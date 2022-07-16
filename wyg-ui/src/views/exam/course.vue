<template>
  <div class="app-container">
    <el-form :model="listQuery" ref="queryForm" :inline="true">
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="listQuery.courseName" size="small" clearable placeholder="课程名称" style="width: 240px;"
                  @keyup.enter.native="handleFilter"/>
      </el-form-item>

      <el-form-item label="老师" prop="teacher">
        <el-input
          v-model="listQuery.teacher"
          placeholder="请输入老师"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="首页显示" prop="showIndex">
        <el-select v-model="listQuery.showIndex" placeholder="请选择首页显示" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否显示" prop="isShow">
        <el-select v-model="listQuery.isShow" placeholder="请选择是否显示" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button size="mini" v-waves  type="primary" icon="el-icon-search" @click="handleFilter">{{
          $t('table.search') }}
        </el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button size="mini" v-hasPermi="['exam:course:add']" type="primary" icon="el-icon-check"
                   @click="handleCreate">{{ $t('table.add') }}
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button size="mini" v-hasPermi="['exam:course:del']" type="danger" icon="el-icon-delete"
                   @click="handleDeletes">{{ $t('table.del') }}
        </el-button>
      </el-col>
    </el-row>


    <spinner-loading v-if="listLoading"/>
    <el-table
      ref="multipleTable"
      :key="tableKey"
      :data="list"
      :default-sort="{ prop: 'id', order: 'descending' }"
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
      @sort-change="sortChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column label="课程名称" align="left" prop="courseName"  width="160" />
<!--      <el-table-column :label="$t('table.college')" prop="college">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.college }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column :label="$t('table.major')" prop="major">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.major }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column :label="$t('table.teacher')" prop="teacher">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.teacher }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="点击数" align="center" prop="hits" />
      <el-table-column label="评分" align="center" prop="rating" />
      <el-table-column label="购买数" align="center" prop="buyCount" />
      <el-table-column label="课时数" align="center" prop="period" />

      <el-table-column label="排序" align="center" prop="sequence" />

      <el-table-column label="首页显示" align="center" prop="showIndex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.showIndex"/>
        </template>
      </el-table-column>
      <el-table-column label="是否显示" align="center" prop="isShow">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isShow"/>
        </template>
      </el-table-column>

      <el-table-column :label="$t('table.cover')" prop="teacher">
        <template slot-scope="scope">
          <ImagePreview :src="scope.row.cover" :width="64"/>
        </template>
      </el-table-column>

      <el-table-column :label="$t('table.modifier')" property="modifier" min-width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.modifier }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.modifyDate')" property="updateTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.modifyDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="status-col" width="100">
        <template slot-scope="scope">

          <el-dropdown size="mini">
            <span class="el-dropdown-link">
              操作<i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-hasPermi="['exam:course:edit']">
                <a @click="handleUpdate(scope.row)">
                  <span><i class="el-icon-edit"></i>{{ $t('table.edit') }}</span>
                </a>
              </el-dropdown-item>
              <el-dropdown-item v-hasPermi="['exam:course:del']">
                <a @click="handleDelete(scope.row)">
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

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="60%" top="10vh">
      <el-form ref="dataForm" :rules="rules" :model="temp" :label-position="labelPosition" label-width="100px">
        <el-row>

          <el-col :span="18">

            <el-row>
              <el-col :span="12">
                <el-form-item :label="$t('table.courseName')" prop="courseName">
                  <el-input v-model="temp.courseName"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label="$t('table.college')" prop="college">
                  <el-input v-model="temp.college"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item :label="$t('table.major')" prop="major">
                  <el-input v-model="temp.major"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label="$t('table.teacher')" prop="teacher">
                  <el-input v-model="temp.teacher"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="课时数" prop="period">
                  <el-input type="Number" v-model="temp.period"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="排序" prop="sequence">
                  <el-input type="Number" v-model="temp.sequence"/>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="是否显示">
                  <el-radio-group v-model="temp.isShow">
                    <el-radio
                      v-for="dict in dict.type.sys_yes_no"
                      :key="dict.value"
                      :label="dict.value"
                    >{{dict.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="首页显示">
                  <el-radio-group v-model="temp.showIndex">
                    <el-radio
                      v-for="dict in dict.type.sys_yes_no"
                      :key="dict.value"
                      :label="dict.value"
                    >{{dict.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item :label="$t('table.courseDescription')">
                  <el-input :autosize="{ minRows: 3, maxRows: 5}" :placeholder="$t('table.courseDescription')"
                            v-model="temp.courseDescription" type="textarea"/>
                </el-form-item>
              </el-col>
            </el-row>

          </el-col>

          <el-col :span="5" :offset="1">
            <el-row>
              <ImagePreview :src="temp.cover+'.256.jpg'"/>
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
        <el-button v-if="dialogStatus === 'create'" type="primary" @click="createData">{{ $t('table.confirm') }}
        </el-button>
        <el-button v-else type="primary" @click="updateData">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>

    <MaterialBox  :visible.sync="showMaterialBox" :max="1" :clear="true" @change="selectedMaterial"/>

  </div>
</template>

<script>
  import {fetchCourseList, addObj, putObj, delObj, delAllObj} from '@/api/exam/course'
  import waves from '@/directive/waves'
  import {mapGetters} from 'vuex'
  import {checkMultipleSelect, notifySuccess, messageSuccess} from '@/utils/util'
  import SpinnerLoading from '@/components/SpinnerLoading'

  export default {
    name: 'CourseManagement',
    components: {
      SpinnerLoading
    },
    directives: {
      waves
    },
    dicts: ['sys_yes_no'],
    data() {
      return {
        showMaterialBox:false,
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          roleName: undefined,
          sort: 'id',
          order: 'descending'
        },
        temp: {
          id: '',
          courseName: '',
          college: '',
          major: '',
          teacher: '',
          courseDescription: '',
          cover:'',
          sequence:1,
          period:8
        },
        checkedKeys: [],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新建'
        },
        rules: {
          courseName: [{required: true, message: '请输入课程名称', trigger: 'change'}]
        },
        downloadLoading: false,
        labelPosition: 'right',
        // 多选
        multipleSelection: []
      }
    },
    created() {
      this.getList()
    },
    computed: {
      ...mapGetters([
        'elements',
        'permissions'
      ])
    },
    methods: {
      getList() {
        this.listLoading = true
        fetchCourseList(this.listQuery).then(response => {
          this.list = response.rows
          this.total = parseInt(response.total)
          setTimeout(() => {
            this.listLoading = false
          }, 500)
        }).catch(() => {
          this.listLoading = false
        })
      },
      //打开文件管理
      openMaterial(){
        this.showMaterialBox = true;
      },
      selectedMaterial(materialList){
        if(materialList==null || materialList.length<1) return;
        var image=materialList[materialList.length-1];
        this.temp.cover=image.materialUrl;
      },
      handleFilter() {
        this.listQuery.pageNum = 1
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val
        this.getList()
      },
      handleModifyStatus(row, status) {
        row.status = status
        putObj(row).then(() => {
          this.dialogFormVisible = false
          messageSuccess(this, '操作成功')
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      sortChange(column, prop, order) {
        this.listQuery.sort = column.prop
        this.listQuery.order = column.order
        this.getList()
      },
      // 点击选中
      handleRowClick(row) {
        this.$refs.multipleTable.toggleRowSelection(row, true)
      },
      resetTemp() {
        this.temp = {
          id: '',
          courseName: '',
          college: '',
          major: '',
          teacher: '',
          courseDescription: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            addObj(this.temp).then(() => {
              this.list.unshift(this.temp)
              this.dialogFormVisible = false
              this.getList()
              notifySuccess(this, '创建成功')
            })
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.temp.status = parseInt(this.temp.status)
        this.temp.readonly = true
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
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
      handleDelete(row) {
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
        }).catch(() => {
        })
      },
      // 批量删除
      handleDeletes() {
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
            delAllObj(ids).then(() => {
              this.dialogFormVisible = false
              this.getList()
              notifySuccess(this, '删除成功')
            })
          }).catch(() => {
          })
        }
      }
    }
  }
</script>
