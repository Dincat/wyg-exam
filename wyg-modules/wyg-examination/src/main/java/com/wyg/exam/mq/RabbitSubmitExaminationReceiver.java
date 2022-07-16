package com.wyg.exam.mq;


import com.wyg.exam.constants.MqConstant;
import com.wyg.exam.domain.Answer;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.enums.SubmitStatusEnum;
import com.wyg.exam.service.IAnswerService;
import com.wyg.exam.service.IExaminationRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 提交考试消息消费者
 *
 * @author tangyi
 * @date 2019/5/3 14:55
 */
@Slf4j
@AllArgsConstructor
@Service
public class RabbitSubmitExaminationReceiver {

    @Autowired
    IAnswerService answerService;

    @Autowired
    IExaminationRecordService examRecordService;

    /**
     * 处理提交考试逻辑：统计错题，计算成绩等
     * 1. 先更新考试记录的状态为正在计算
     * 2. 更新成功则执行提交逻辑
     *
     * @param answer answer
     * @author tangyi
     * @date 2019/05/03 14:56
     */
    @RabbitListener(queues = {MqConstant.SUBMIT_EXAMINATION_QUEUE})
    public void submitExamination(Answer answer) {
        //log.debug("examRecordId: {}, modifier: {}", answer.getExamRecordId(), answer.getModifier());
        try {
            // 异步提交会丢失tenantCode，需要手动设置
            //TenantContextHolder.setTenantCode(answer.getTenantCode());
            ExaminationRecord examRecord = examRecordService.selectExaminationRecordById(answer.getExamRecordId());
            if (examRecord == null)
                return;
            if (SubmitStatusEnum.UNSUBMITTED.getValue().equals(examRecord.getSubmitStatus()))
                log.warn("Examination: {} not submitted", examRecord.getId());
            if (SubmitStatusEnum.CALCULATE.getValue().equals(examRecord.getSubmitStatus()))
                log.warn("Examination: {} is counting, please do not submit again", examRecord.getId());
            // 更新状态为正在统计
            examRecord.setSubmitStatus(SubmitStatusEnum.CALCULATE.getValue());

            // 更新成功
            if (examRecordService.updateExaminationRecord(examRecord) > 0) {
                log.debug("Examination: {} count success", examRecord.getId());
                answerService.submit(answer);
            } else {
                log.warn("Examination: {} count failed", examRecord.getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
