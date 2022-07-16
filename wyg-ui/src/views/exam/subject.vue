<template>
  <div class="app-container">
    <el-row>
      <el-col :span="4">
        <subject-category-tree v-model="currentCategoryId" @selected="handleCategorySelected"/>
      </el-col>
      <el-col :span="20">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>题目管理</span>
          </div>

          <div class="filter-container">
            <el-form :model="listQuery" ref="queryForm" :inline="true">
              <el-form-item label="题目名称" prop="subjectName">
                <el-input v-model="listQuery.subjectName" placeholder="题目名称" style="width: 200px;" class="filter-item"
                          @keyup.enter.native="handleFilter" size="small" clearable/>
              </el-form-item>

              <el-form-item>
                <el-button v-waves  size="mini" type="primary" icon="el-icon-search" @click="handleFilter">{{
                  $t('table.search') }}
                </el-button>
              </el-form-item>
            </el-form>

            <el-button v-hasPermi="['exam:subject:bank:add']" size="mini" type="primary"
                       icon="el-icon-check" @click="handleCreateSubject">{{ $t('table.add') }}
            </el-button>
            <el-button v-hasPermi="['exam:subject:bank:del']" size="mini" type="danger" icon="el-icon-delete"
                       @click="handleDeletesSubject">{{ $t('table.del') }}
            </el-button>
            <el-button v-hasPermi="['exam:subject:bank:import']" size="mini" type="success" icon="el-icon-upload2"
                       @click="handleImportSubject">{{ $t('table.import') }}
            </el-button>
            <el-button v-hasPermi="['exam:subject:bank:export']" size="mini" type="success" icon="el-icon-download"
                       @click="handleExportSubject">{{ $t('table.export') }}
            </el-button>

          </div>

          <spinner-loading v-if="listLoading"/>
          <el-table
            :data="list"
            highlight-current-row
            style="width: 100%;"
            @selection-change="handleSubjectSelectionChange"
            @sort-change="sortSubjectChange">
            <el-table-column type="selection" width="55"/>
            <el-table-column :label="$t('table.subjectName')" min-width="120">
              <template slot-scope="scope">
                <span v-html="scope.row.subjectName"></span>
              </template>
            </el-table-column>
            <el-table-column :label="$t('table.subject.type')" width="120">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.subject_types" :value="scope.row.type"/>
              </template>
            </el-table-column>
            <el-table-column :label="$t('table.subject.score')" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.subjectId }}</span>
              </template>
            </el-table-column>
            <el-table-column :label="$t('table.modifier')" property="modifier" width="120">
              <template slot-scope="scope">
                <span>{{ scope.row.modifier }}</span>
              </template>
            </el-table-column>
            <el-table-column :label="$t('table.modifyDate')" property="updateTime" width="150">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.modifyDate) }}</span>
              </template>
            </el-table-column>
            <el-table-column :label="$t('table.actions')" class-name="status-col" width="100px">
              <template slot-scope="scope">
                <el-dropdown>
                  <span class="el-dropdown-link">
                    操作<i class="el-icon-caret-bottom el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item v-hasPermi="['exam:subject:bank:edit']">
                      <a @click="handleUpdateSubject(scope.row)">
                        <span><i class="el-icon-edit"></i>{{ $t('table.edit') }}</span>
                      </a>
                    </el-dropdown-item>
                    <el-dropdown-item>
                      <a @click="handleViewSubject(scope.row)">
                        <span><i class="el-icon-view"></i>{{ $t('table.preview') }}</span>
                      </a>
                    </el-dropdown-item>
                    <el-dropdown-item v-hasPermi="['exam:subject:bank:del']">
                      <a @click="handleDeleteSubject(scope.row)">
                        <span><i class="el-icon-delete"></i>{{ $t('table.delete') }}</span>
                      </a>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination v-show="total>0" :current-page="listQuery.pageNum" :page-sizes="[10,20,30, 50]"
                           :page-size="listQuery.pageSize" :total="total" background
                           layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"/>
          </div>
        </el-card>
      </el-col>
    </el-row>


    <!--题目信息表单-->
    <el-dialog :title="textMap[subjectFormStatus]" :visible.sync="dialogSubjectFormVisible" width="80%" top="5vh">
      <el-form ref="dataSubjectForm" :rules="subjectRules" :model="tempSubject" :label-position="labelPosition"
               label-width="100px">
        <el-tabs v-model="activeName" @tab-click="handleTabChange">
          <!-- 单选题 -->
          <el-tab-pane label="单选题" name="0" :disabled="tempSubject.type !== 0 && subjectFormStatus !== 'create'">
            <choices ref="choices" subjectInfo="tempSubject"/>
          </el-tab-pane>
          <!-- 多选题 -->
          <el-tab-pane label="多选题" name="3" :disabled="tempSubject.type !== 3 && subjectFormStatus !== 'create'">
            <multiple-choices ref="multipleChoices" subjectInfo="tempSubject"/>
          </el-tab-pane>
          <!-- 简答题 -->
          <el-tab-pane label="简答题" name="1" :disabled="tempSubject.type !== 1 && subjectFormStatus !== 'create'">
            <short-answer ref="shortAnswer" subjectInfo="tempSubject"/>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSubjectFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="subjectFormStatus === 'create'" type="primary" @click="createSubjectData">{{ $t('table.save')
          }}
        </el-button>
        <el-button v-else type="primary" @click="updateSubjectData">{{ $t('table.save') }}</el-button>
        <el-button type="primary" @click="updateAndAddSubjectData">{{ $t('table.saveAndAdd') }}</el-button>
      </div>
    </el-dialog>

    <!-- 导入题目 -->
    <el-dialog :visible.sync="dialogImportVisible" :title="$t('table.import')">
      <el-row>
        <el-col :span="24">
          <el-upload
            drag
            :multiple="false"
            :auto-upload="true"
            :before-upload="beforeUploadSubjectUpload"
            :on-progress="handleUploadSubjectProgress"
            :on-success="handleUploadSubjectSuccess"
            :action="importUrl"
            :headers="headers"
            :data="params"
            style="text-align: center;">
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传xlsx文件</div>
          </el-upload>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 预览题目 -->
    <el-dialog title="预览题目" :visible.sync="dialogViewVisible" width="60%" top="10vh">
      <div class="subject-title">
        <span class="subject-title-content" v-html="tempSubject.subjectName"/>
        <span class="subject-title-content">&nbsp;({{tempSubject.score}})分</span>
      </div>
      <ul v-if="tempSubject.type === 0 || tempSubject.type === 3" class="subject-options">
        <li class="subject-option" v-for="(option) in tempSubject.options" :key="option.id">
          <input class="toggle" type="checkbox" >
          <label><span class="subject-option-prefix">{{option.optionName}}&nbsp;</span><span
            v-html="option.optionContent" class="subject-option-prefix"></span></label>
        </li>
      </ul>
      <ul v-if="tempSubject.type === 2" class="subject-options">
        <li class="subject-option">
          <input class="toggle" type="checkbox">
          <label><span class="subject-option-prefix">正确</span></label>
        </li>
        <li class="subject-option">
          <input class="toggle" type="checkbox">
          <label><span class="subject-option-prefix">错误</span></label>
        </li>
      </ul>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogViewVisible = false">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

  import {
    fetchSubjectList,
    addSubject,
    getSubject,
    putSubject,
    delSubject,
    delAllSubject,
    exportSubject
  } from '@/api/exam/subject'
  import {mapGetters} from 'vuex'
  import {getToken} from '@/utils/auth'
  import {checkMultipleSelect, exportExcel, notifySuccess, isNotEmpty} from '@/utils/util'
  import waves from '@/directive/waves'
  import SpinnerLoading from '@/components/SpinnerLoading'
  import Choices from '@/components/Subjects/Choices'
  import MultipleChoices from '@/components/Subjects/MultipleChoices'
  import ShortAnswer from '@/components/Subjects/ShortAnswer'
  import SubjectCategoryTree from '@/components/Subjects/SubjectCategoryTree'

  export default {
    name: 'SubjectManagement',
    dicts: ['subject_types'],
    components: {SpinnerLoading, Choices, MultipleChoices, ShortAnswer,SubjectCategoryTree},
    directives: {
      waves
    },
    data() {
      return {
        baseUrl: '/exam',
        list: null,
        total: 0,
        formAdd: true,
        formStatus: '',
        showElement: false,
        typeOptions: ['0', '1'],
        listLoading: true,
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          subjectName: undefined,
          categoryId: undefined,
          examinationId: undefined,
          sort: 'id',
          order: 'descending'
        },
        labelPosition: 'right',
        subjectFormStatus: '',
        textMap: {
          update: '编辑',
          create: '新建'
        },
        category: undefined,

        // 题目临时信息
        tempSubject: {
          id: '',
          examinationId: undefined,
          categoryId: undefined,
          subjectName: '',
          type: 0,
          choicesType: 0,
          options: [
            {subjectChoicesId: '', optionName: 'A', optionContent: ''},
            {subjectChoicesId: '', optionName: 'B', optionContent: ''},
            {subjectChoicesId: '', optionName: 'C', optionContent: ''},
            {subjectChoicesId: '', optionName: 'D', optionContent: ''}
          ],
          answer: {
            subjectId: '',
            answer: '',
            answerType: '',
            score: ''
          },
          score: 5,
          analysis: '',
          level: 2
        },
        // 选择题类型
        tempChoiceType: [
          {type: 0, name: '单选题'},
          {type: 1, name: '简答题'},
          {type: 2, name: '判断题'},
          {type: 3, name: '多选题'}
        ],
        currentCategoryId: '',

        // 表单校验规则
        subjectRules: {
          subjectName: [{required: true, message: '请输入题目名称', trigger: 'change'}],
          score: [{required: true, message: '请输入题目分值', trigger: 'change'}],
          answer: [{required: true, message: '请输入答案', trigger: 'change'}],
          serialNumber: [{required: true, message: '请输入题目序号', trigger: 'change'}]
        },

        // 题目
        dialogSubjectFormVisible: false,
        // 导入窗口状态
        dialogImportVisible: false,
        // 导出窗口状态
        dialogExportVisible: false,
        // 预览窗口状态
        dialogViewVisible: false,
        // 选择的菜单
        multipleSelection: [],
        importUrl: '/api/exam/subject/import',
        // 多选题目
        multipleSubjectSelection: [],
        uploading: false,
        uploadingSubject: false,
        percentageSubject: 0,
        params: {
          categoryId: undefined
        },
        headers: {
          Authorization: 'Bearer ' + getToken()
        },
        tinymce: {
          type: 1, // 类型 0：题目名称，1：选项A，2：选择B，3：选项C，4：选项D
          dialogTinymceVisible: false,
          tempValue: '',
          currentEdit: -1
        },
        activeName: '0',
        choicesContent: '',
        shortAnswerEditorContent: '',
        // 编辑对象
        tinymceEdit: {
          subjectName: -1,
          optionA: 0,
          optionB: 1,
          optionC: 2,
          optionD: 3,
          answer: 4,
          analysis: 5
        }
      }
    },
    created() {
      this.handleSubjectManagement()
    },
    computed: {
      ...mapGetters([
        'elements',
      ])
    },
    methods: {
      handleCategorySelected(data){
        this.currentCategoryId = data.id;
        this.listQuery.categoryId = this.currentCategoryId;
        this.params.categoryId = this.currentCategoryId;
        // 获取题目信息
        this.handleSubjectManagement();
      },
      handleFilter() {
        this.listQuery.pageNum = 1
        this.handleSubjectManagement()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.handleSubjectManagement()
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val
        this.handleSubjectManagement()
      },
      handleSubjectSelectionChange(val) {
        this.multipleSubjectSelection = val
      },
      // 切换题目类型
      changeSubjectType(value) {
        console.log(value)
      },

      resetTempSubject(score) {
        this.tempSubject = {
          id: '',
          examinationId: undefined,
          categoryId: undefined,
          subjectName: '',
          type: 0,
          choicesType: 0,
          options: [
            {subjectChoicesId: '', optionName: 'A', optionContent: ''},
            {subjectChoicesId: '', optionName: 'B', optionContent: ''},
            {subjectChoicesId: '', optionName: 'C', optionContent: ''},
            {subjectChoicesId: '', optionName: 'D', optionContent: ''}
          ],
          answer: {
            subjectId: '',
            answer: '',
            answerType: '',
            score: ''
          },
          score: score,
          analysis: '',
          level: 2
        }
        this.$refs['choices'].resetTempSubject(score)
        this.$refs['shortAnswer'].resetTempSubject(score)
        this.$refs['multipleChoices'].resetTempSubject(score)
      },
      // 加载题目
      handleSubjectManagement() {
        this.listLoading = true
        fetchSubjectList(this.listQuery).then(response => {
          if (isNotEmpty(response.data) && response.rows.length > 0) {
            for (let i = 0; i < response.rows.length; i++) {
              const subject = response.rows[i]
              subject.type = parseInt(subject.type)
              subject.level = parseInt(subject.level)
            }
          }
          this.list = response.rows
          this.total = parseInt(response.total)
          this.listLoading = false
        }).catch(() => {
          this.listLoading = false
        })
      },
      // 点击新建题目
      handleCreateSubject() {

        this.$router.push({
          path: `/exam/subjects/detail/${this.currentCategoryId}-undefined-0-1`
        })
      },
      // 修改题目
      handleUpdateSubject(row) {
        console.log(row)
        this.$router.push({
          path: `/exam/subjects/detail/${row.categoryId}-${row.subjectId}-${row.type}-1`
        })
      },
      // 删除题目
      handleDeleteSubject(row) {
        this.$confirm('确定要删除吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delSubject(row.subjectId, {type: row.type}).then(() => {
            this.dialogSubjectFormVisible = false
            this.handleSubjectManagement()
            notifySuccess(this, '删除成功')
          })
        })
      },
      // 保存题目
      createSubjectData() {
        const ref = this.getSubjectRef()
        if (ref.validate()) {
          let subjectInfo = ref.getSubjectInfo()
          subjectInfo.categoryId = this.currentCategoryId
          addSubject(subjectInfo).then(() => {
            this.dialogSubjectFormVisible = false
            notifySuccess(this, '创建成功')
          })
        }
      },
      // 更新题目
      updateSubjectData() {
        const ref = this.getSubjectRef()
        if (ref.validate()) {
          const subjectInfo = ref.getSubjectInfo()
          putSubject(subjectInfo).then(() => {
            this.dialogSubjectFormVisible = false
            notifySuccess(this, '更新成功')
          })
        }
      },
      // 更新并添加题目
      updateAndAddSubjectData() {
        const ref = this.getSubjectRef()
        if (ref.validate()) {
          const subjectInfo = ref.getSubjectInfo()
          if (isNotEmpty(this.currentCategoryId)) {
            subjectInfo.categoryId = this.currentCategoryId
          }
          // 创建
          if (this.subjectFormStatus === 'create') {
            addSubject(subjectInfo).then(() => {
              this.resetTempSubject(subjectInfo.score)
              this.subjectFormStatus = 'create'
              ref.clearValidate()
              notifySuccess(this, '创建成功')
            })
          } else {
            // 更新
            putSubject(subjectInfo).then(() => {
              this.resetTempSubject(subjectInfo.score)
              this.subjectFormStatus = 'create'
              // 绑定分类ID
              this.tempSubject.categoryId = this.currentCategoryId
              ref.clearValidate()
              notifySuccess(this, '更新成功')
            })
          }
        }
      },
      // 批量删除
      handleDeletesSubject() {
        if (checkMultipleSelect(this.multipleSubjectSelection, this)) {
          let ids = []
          for (let i = 0; i < this.multipleSubjectSelection.length; i++) {
            ids.push(this.multipleSubjectSelection[i].id)
          }
          this.$confirm('确定要删除吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delAllSubject(ids).then(() => {
              this.handleSubjectManagement()
              notifySuccess(this, '删除成功')
            })
          }).catch(() => {
          })
        }
      },
      // 查看题目
      handleViewSubject(row) {
        // 加载题目信息
        getSubject(row.subjectId, {type: row.type}).then(response => {
          this.tempSubject = response.data
          this.dialogViewVisible = true
        })
      },
      // 点击排序按钮
      sortSubjectChange(column, prop, order) {
        this.listQuery.sort = column.prop
        this.listQuery.order = column.order
        this.handleSubjectManagement()
      },
      // 导出
      handleExportSubject() {
        // 没选择题目，导出所有
        if (this.multipleSubjectSelection.length === 0 || this.currentCategoryId === '') {
          this.$confirm('是否导出所有题目?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'success'
          }).then(() => {
            exportSubject([], '', this.currentCategoryId).then(response => {
              // 导出Excel
              exportExcel(response)
            })
          }).catch(() => {

          })
        } else {
          // 导出选中
          this.$confirm('是否导出选中的题目?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'success'
          }).then(() => {
            let ids = []
            for (let i = 0; i < this.multipleSubjectSelection.length; i++) {
              ids.push(this.multipleSubjectSelection[i].id)
            }
            exportSubject(ids, '', '').then(response => {
              // 导出Excel
              exportExcel(response)
            })
          }).catch(() => {
          })
        }
      },
      // 导入
      handleImportSubject() {
        if (this.currentCategoryId === '') {
          this.$message({
            message: '请选择分类',
            type: 'warning'
          })
          return
        }
        this.dialogImportVisible = true
      },
      // 上传前
      beforeUploadSubjectUpload(file) {
        const isExcel = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        const isLt10M = file.size / 1024 / 1024 < 10
        if (!isExcel) {
          this.$message.error('上传附件只能是 excel 格式!')
        }
        if (!isLt10M) {
          this.$message.error('上传附件大小不能超过 10MB!')
        }
        return isExcel && isLt10M
      },
      handleUploadSubjectProgress(event, file, fileList) {
        this.uploadingSubject = true
        this.percentageSubject = parseInt(file.percentage.toFixed(0))
      },
      // 上传成功
      handleUploadSubjectSuccess() {
        this.dialogImportVisible = false
        this.handleSubjectManagement()
        notifySuccess(this, '导入成功')
        this.uploadingSubject = false
      },
      // 切换题型
      handleTabChange(tab, event) {
        this.tempSubject.type = parseInt(tab.name)
        // 更新组件里的题目信息
        this.updateComponentSubjectInfo()
      },
      updateCurrentTag(type) {
        this.activeName = type + ''
      },
      resetActiveName() {
        // 重置选项卡至单选题
        this.activeName = '0'
      },
      // 更新组件里的题目信息
      updateComponentSubjectInfo() {
        // 单选题
        const ref = this.getSubjectRef()
        if (isNotEmpty(ref)) {
          ref.setSubjectInfo(this.tempSubject)
        }
      },
      getSubjectRef() {
        let ref
        switch (this.activeName) {
          case '0':
            ref = this.$refs['choices']
            break
          case '1':
            ref = this.$refs['shortAnswer']
            break
          case '3':
            ref = this.$refs['multipleChoices']
            break
        }
        return ref
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>


  .subject-title {
    font-size: 18px;
    line-height: 22px;

    .subject-title-number {
      display: inline-block;
      line-height: 22px;
    }

    .subject-title-content {
      display: inline-block;
    }
  }

  .subject-options {
    margin: 0;
    padding: 0;
    list-style: none;

    > li {
      position: relative;
      font-size: 24px;

      .toggle {
        opacity: 0;
        text-align: center;
        width: 35px;
        /* auto, since non-WebKit browsers doesn't support input styling */
        height: auto;
        position: absolute;
        top: 0;
        bottom: 0;
        margin: auto 0;
        border: none;
        /* Mobile Safari */
        -webkit-appearance: none;
        appearance: none;
      }

      .toggle + label {
        background-image: url('data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E');
        background-repeat: no-repeat;
        background-position: center left;
        background-size: 30px;
      }

      .toggle:checked + label {
        background-size: 30px;
        background-image: url('data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E');
      }

      label {
        word-break: break-all;
        padding: 10px 10px 10px 45px;
        display: block;
        line-height: 1.0;
        transition: color 0.4s;
      }

      /* 选项名称 */
      .subject-option-prefix {
        font-size: 16px;
        display: inline-block
      }
    }
  }
</style>
