<template>
  <div class="app-container">
    <el-form :model="listQuery" ref="queryForm"  :inline="true">
      <el-form-item :label="$t('table.examinationName')" prop="examinationName">
        <el-input clearable size="small" :placeholder="$t('table.examinationName')" v-model="listQuery.examinationName" style="width: 240px;" class="filter-item"
                  @keyup.enter.native="handleFilter"/>
      </el-form-item>

      <el-form-item label="能否重复作答" prop="canRepeat">
        <el-select v-model="listQuery.canRepeat" placeholder="能否重复作答" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="试卷状态" prop="status">
        <el-select v-model="listQuery.status" placeholder="能否重复作答" clearable size="small">
          <el-option
            v-for="dict in dict.type.exam_publish_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button size="mini" v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ $t('table.search') }}</el-button>
      </el-form-item>
    </el-form>


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button size="mini" v-hasPermi="['exam:exam:add']" class="filter-item" type="primary" icon="el-icon-check" @click="handleCreate">{{ $t('table.add') }}</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button size="mini" v-hasPermi="['exam:exam:del']" class="filter-item" type="danger" icon="el-icon-delete" @click="handleDeletes">{{ $t('table.del') }}</el-button>
      </el-col>
    </el-row>


    <spinner-loading v-if="listLoading"/>
    <!--试卷列表-->
    <el-table
      :key="tableKey"
      :data="list"
      :default-sort="{ prop: 'id', order: 'descending' }"
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
      @sort-change="sortChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column :label="$t('table.examinationName')" min-width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.examinationName }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.examinationType')">
        <template slot-scope="scope">
<!--          <span>{{ scope.row.type | examTypeFilter }}</span>-->
          <dict-tag :options="dict.type.examination_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.course')">
        <template slot-scope="scope">
          <span>{{ scope.row | courseFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.totalScore')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.totalScore }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作答时长（分钟）" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.duration }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="canRepeat" label="能否重复作答" width="150" align="center">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.canRepeat"/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.status')" align="center">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.exam_publish_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.modifier')">
        <template slot-scope="scope">
          <span>{{ scope.row.modifier }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.modifyDate')">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifyDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="status-col" width="100">
        <template slot-scope="scope">
          <el-dropdown>
            <span class="el-dropdown-link">
              操作<i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-hasPermi="['exam:exam:edit']">
                <a @click="handleUpdate(scope.row)">
                  <span><i class="el-icon-edit"></i>{{ $t('table.edit') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.status == 1" v-hasPermi="['exam:exam:edit']">
                <a @click="handlePublic(scope.row, 0)">
                  <span><i class="el-icon-check"></i>{{ $t('table.public') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.status == 0" v-hasPermi="['exam:exam:edit']">
                <a @click="handlePublic(scope.row, 1)">
                  <span><i class="el-icon-close"></i>{{ $t('table.withdraw') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item v-hasPermi="['exam:exam:del']" >
                <a @click="handleDelete(scope.row)">
                  <span><i class="el-icon-delete"></i>{{ $t('table.delete') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item v-has-permi="['exam:exam:subject']">
                <a @click="handleSubjectManagement(scope.row)">
                  <span><i class="el-icon-document"></i>{{ $t('table.subjectManagement') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item>
                <a @click="handleShare(scope.row)">
                  <span><i class="el-icon-share"></i>{{ $t('table.share') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item>
                <a @click="handleShareV2(scope.row)">
                  <span><i class="el-icon-share"></i>{{ $t('table.shareV2') }}</span>
                </a>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination v-show="total>0" :current-page="listQuery.pageNum" :page-sizes="[10,20,30, 50]" :page-size="listQuery.pageSize" :total="total" background layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>

    <!--考试信息表单-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="70%" top="10vh">
      <el-form ref="dataForm" :rules="rules" :model="temp" :label-position="labelPosition" label-width="120px">
        <el-row>
          <el-col :span="18">
            <el-row>
              <el-col :span="24">
                <el-form-item :label="$t('table.examinationName')" prop="examinationName">
                  <el-input v-model="temp.examinationName" :readonly="temp.readonly"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item :label="$t('table.totalScore')" prop="totalScore">
                  <el-input v-model="temp.totalScore" type="Number"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label="$t('table.course')" prop="course.id">
                  <el-input v-model="temp.course.courseName" @focus="selectCourse"/>
                  <input v-model="temp.course.id" type="hidden">
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item :label="$t('table.startTime')" prop="startTime">
<!--                  {{temp.startTime}}-->
                  <el-date-picker v-model="temp.startTime" :placeholder="$t('table.startTime')" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label="$t('table.endTime')" prop="endTime">
                  <el-date-picker v-model="temp.endTime" :placeholder="$t('table.endTime')" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="作答时长(分钟)" prop="duration">
                  <el-input v-model="temp.duration" type="Number"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="可否重复做题">
                  <el-radio-group v-model="temp.canRepeat">
                    <el-radio
                      v-for="dict in dict.type.sys_yes_no"
                      :key="dict.value"
                      :label="dict.value"
                    >{{dict.label}}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item :label="$t('table.examinationType')" prop="type">
                  <el-select v-model="temp.type" clearable class="filter-item">
                    <el-option v-for="item in examType" :key="item.key" :label="item.displayName" :value="item.key"/>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label="$t('table.status')">
                  <el-radio-group v-model="temp.status">
                    <el-radio :label="0">已发布</el-radio>
                    <el-radio :label="1">草稿</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item :label="$t('table.attention')">
                  <el-input :autosize="{ minRows: 3, maxRows: 5}" :placeholder="$t('table.attention')" v-model="temp.attention" type="textarea"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item :label="$t('table.remark')">
                  <el-input :autosize="{ minRows: 3, maxRows: 5}" v-model="temp.remark" type="textarea" placeholder="备注"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-row>
              <ImagePreview :src="avatar"/>
            </el-row>

            <el-row align="middle">
              <el-button size="small" @click="openMaterial">
                选择
                <i class="el-icon-upload el-icon--right"></i>
              </el-button>
            </el-row>

          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="dialogStatus === 'create'" type="primary" @click="createData">{{ $t('table.confirm') }}</el-button>
        <el-button v-else type="primary" @click="updateData">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>

    <!--课程选择弹窗-->
    <el-dialog :visible.sync="dialogCourseVisible" :title="$t('table.course')">
      <el-table v-loading="course.listLoading" :data="course.list" @row-dblclick="selectedCourse">
        <el-table-column :label="$t('table.courseName')" property="courseName" width="150">
          <template slot-scope="scope">
            <span>{{ scope.row.courseName }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.college')" property="college" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.college }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.major')" property="major">
          <template slot-scope="scope">
            <span>{{ scope.row.major }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.teacher')" property="teacher">'
          <template slot-scope="scope">
            <span>{{ scope.row.teacher }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <MaterialBox  :visible.sync="showMaterialBox" :max="1" :clear="true" @change="selectedMaterial"/>

    <!-- 二维码 -->
    <el-dialog :visible.sync="dialogQrCodeVisible" title="二维码" width="30%" top="12vh">
      <div class="qrCodeInfo">
        <el-button type="success" icon="el-icon-check" circle></el-button>
        <p>二维码生成成功，扫一扫即可在手机答题</p>
        <img :src="qrCodeUrl" alt="二维码">
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, addObj, putObj, delObj, delAllObj } from '@/api/exam/exam'
import { fetchCourseList } from '@/api/exam/course'
import waves from '@/directive/waves'
import { mapGetters, mapState } from 'vuex'
import { getToken } from '@/utils/auth'
import { checkMultipleSelect, isNotEmpty, notifySuccess, notifyFail, messageSuccess } from '@/utils/util'
import { getMaterialInfo } from '@/api/file/material'
import SpinnerLoading from '@/components/SpinnerLoading'
import Choices from '@/components/Subjects/Choices'
import MultipleChoices from '@/components/Subjects/MultipleChoices'
import ShortAnswer from '@/components/Subjects/ShortAnswer'
import { apiList } from '@/const/constant'
import { examType } from '@/utils/constant'

export default {
  name: 'ExamManagement',
  dicts: ['examination_type','exam_publish_status','sys_yes_no','exam_publish_status'],
  directives: {
    waves
  },
  components: {SpinnerLoading, Choices, MultipleChoices, ShortAnswer },
  filters: {
    courseFilter (row) {
      if (isNotEmpty(row.course) && isNotEmpty(row.course.courseName)) {
        return row.course.courseName
      }
      return ''
    }
  },
  data () {
    return {

      showMaterialBox:false,

      baseUrl: '/exam',
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        canRepeat:'',
        status:'',
        pageNum: 1,
        pageSize: 10,
        sort: 'id',
        order: 'descending'
      },
      // 课程
      course: {
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          sort: 'id',
          order: 'descending'
        },
        list: null,
        total: null,
        listLoading: true
      },
      // 考试临时信息
      temp: {
        id: '',
        examinationName: '',
        type: 0,
        attention: '',
        startTime: '',
        endTime: '',
        duration: '',
        totalScore: '',
        totalSubject: '0',
        canRepeat:'N',
        status: 1,
        avatarId: null,
        collegeId: '',
        majorId: '',
        course: {
          id: '',
          courseName: ''
        },
        remark: ''
      },
      avatar: null,
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新建'
      },
      // 校验规则
      rules: {
        examinationName: [{ required: true, message: '请输入考试名称', trigger: 'change' }],
        courseId: [{ required: true, message: '请输入考试所属课程', trigger: 'change' }],
        startTime: [{ required: true, message: '请选择开始时间', trigger: 'change' }],
        endTime: [{ required: true, message: '请选择结束时间', trigger: 'change' }],
        duration: [{ required: true, message: '请输入作答时长', trigger: 'change' }],
        totalScore: [{ required: true, message: '请输入总分', trigger: 'change' }]
      },
      downloadLoading: false,
      labelPosition: 'right',
      // 按钮权限
      dialogCourseVisible: false,
      dialogQrCodeVisible: false,
      courseData: [],
      // 多选考试
      multipleSelection: [],
      uploading: false,
      percentage: 0,
      percentageSubject: 0,
      activeName: '0',
      qrCodeUrl: '',
      examType: []
    }
  },
  created () {
    // 加载考试列表
    this.getList()
    // 获取课程列表
    fetchCourseList(this.course.listQuery).then(response => {
      this.course.list = [{ id: '', courseName: '请选择' }].concat(response.rows)
      this.course.total = parseInt(response.total)
      this.course.listLoading = false
    })
    Object.keys(examType).forEach(key => {
      this.examType.push({ key: parseInt(key), displayName: examType[key] })
    })
  },
  computed: {
    ...mapGetters([
      'elements',
      'permissions'
    ]),
    ...mapState({
      sysConfig: state => state.sysConfig.sysConfig
    })
  },
  methods: {
    // 加载考试列表
    getList () {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.rows
        this.total = parseInt(response.total)
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 500)
      }).catch(() => {
        this.listLoading = false
      })
    },
    getMaterial(id){
      this.avatar = '';
      getMaterialInfo(id).then(resp=>{
        this.avatar = resp.data.materialUrl+'.256.jpg';
      });
    },
    //打开文件管理
    openMaterial(){
      this.showMaterialBox = true;
    },
    selectedMaterial(materialList){
        if(materialList==null || materialList.length<1) return;

        var image=materialList[materialList.length-1];
        this.temp.avatarId=image.materialId;
        this.avatar=image.materialUrl+'.256.jpg';
    },
    handleFilter () {
      this.listQuery.pageNum = 1
      this.getList()
    },
    handleSizeChange (val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange (val) {
      this.listQuery.pageNum = val
      this.getList()
    },
    handleModifyStatus (row, status) {
      row.status = status
      putObj(row).then(() => {
        this.dialogFormVisible = false
        messageSuccess(this, '操作成功')
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    // 排序事件
    sortChange (column, prop, order) {
      this.listQuery.sort = column.prop
      this.listQuery.order = column.order
      this.getList()
    },
    resetTemp () {
      this.temp = {
        id: '',
        examinationName: '',
        type: 0,
        attention: '',
        startTime: '',
        endTime: '',
        duration: '',
        totalScore: '',
        status: 1,
        avatar: '',
        collegeId: '',
        majorId: '',
        course: {
          id: '',
          courseName: ''
        },
        remark: ''
      }
    },
    handleCreate () {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.totalScore = parseInt(this.temp.totalScore)
          addObj(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.getList()
            notifySuccess(this, '创建成功')
          })
        }
      })
    },
    handleShare (row) {
      this.qrCodeUrl = apiList.exam + 'anonymousUser/generateQrCode/' + row.id
      this.dialogQrCodeVisible = true
    },
    handleShareV2 (row) {
      this.qrCodeUrl = apiList.exam + 'anonymousUser/generateQrCode/v2/' + row.id
      this.dialogQrCodeVisible = true
    },
    handleUpdate (row) {
      this.temp = Object.assign({}, row)
      this.avatar = ''
      if (!isNotEmpty(this.temp.course)) {
        this.temp.course = {
          id: '',
          courseName: ''
        }
      }

      if(this.temp.avatarId)
      // 获取图片的预览地址
      this.getMaterial(this.temp.avatarId);

      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          putObj(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.dialogFormVisible = false
            this.getList()
            notifySuccess(this, '更新成功')
          })
        }
      })
    },
    // 删除
    handleDelete (row) {
      this.$confirm('确定要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delObj(row.id).then(() => {
          this.dialogFormVisible = false
          this.getList()
          notifySuccess(this, '删除成功')
        })
      }).catch(() => {})
    },
    // 批量删除
    handleDeletes () {
      if (checkMultipleSelect(this.multipleSelection, this)) {
        let ids = []
        for (let i = 0; i < this.multipleSelection.length; i++) {
          ids.push(this.multipleSelection[i].id)
        }
        this.$confirm('确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delAllObj({ ids: ids }).then(() => {
            this.getList()
            notifySuccess(this, '删除成功')
          })
        }).catch(() => {})
      }
    },
    // 选择课程
    selectCourse () {
      this.course.listLoading = true
      fetchCourseList(this.course.listQuery).then(response => {
        this.course.list = response.rows
        this.course.total = parseInt(response.total)
        this.course.listLoading = false
      })
      this.dialogCourseVisible = true
    },
    // 双击选择课程
    selectedCourse (row) {
      this.temp.course.id = row.id
      this.temp.course.courseName = row.courseName
      this.dialogCourseVisible = false
    },
    // 加载题目
    handleSubjectManagement (row) {
      this.$router.push({
        path: `/exam/subjects/${row.id}`
      })
    },
    // 发布考试
    handlePublic (row, status) {
      const tempData = Object.assign({}, row)
      tempData.status = status
      putObj(tempData).then(() => {
        this.getList()
        notifySuccess(this, '更新成功')
      })
    },

  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "@/assets/styles/exam/subject.scss";
  .qrCodeInfo {
    text-align: center;
  }
</style>
