<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>题目分类</span>
    </div>

    <el-row>
      <el-button v-hasPermi="['exam:subject:category:add']" class="category-btn" icon="el-icon-plus" size="mini"
                 type="primary"
                 @click="handleAddSuperCategory">主分类
      </el-button>
      <el-button v-hasPermi="['exam:subject:category:add']" class="category-btn" icon="el-icon-plus" size="mini"
                 type="primary"
                 @click="handleAddCategory">子分类
      </el-button>
      <el-button v-hasPermi="['exam:subject:category:edit']" class="category-btn" icon="el-icon-edit" size="mini"
                 type="primary" @click="handleUpdateCategory">{{ $t('table.edit') }}
      </el-button>
      <el-button v-hasPermi="['exam:subject:category:del']" class="category-btn" icon="el-icon-delete" size="mini"
                 type="danger" @click="handleDeleteCategory">{{ $t('table.del') }}
      </el-button>
    </el-row>
    <el-row>
      <div class="tree-container">
        <el-tree
          :data="treeData"
          :filter-node-method="filterNode"
          :props="defaultProps"
          class="filter-tree"
          node-key="id"
          highlight-current
          accordion
          :show-checkbox="showCheckbox"
          @node-click="getNodeData"
          @node-expand="nodeExpand"
          @node-collapse="nodeCollapse"
          @check="checkHandler"
        />
      </div>
    </el-row>

    <!--题目分类信息表单-->
    <el-dialog :title="textMap[categoryFormStatus]" :visible.sync="dialogCategoryFormVisible" width="30%" top="20vh">
      <el-form ref="dataCategoryForm" :rules="categoryRules" :model="tempCategory" :label-position="labelPosition"
               label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item :label="$t('table.categoryName')" prop="categoryName">
              <el-input :placeholder="$t('table.categoryName')" v-model="tempCategory.categoryName"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item :label="$t('table.sort')" prop="categoryName">
              <el-input :placeholder="$t('table.sort')" v-model="tempCategory.sort"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">


            <el-form-item label="是否启用" prop="status">
              <el-radio-group v-model="tempCategory.status">
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
            <el-form-item :label="$t('table.categoryDesc')" prop="categoryDesc">
              <el-input :placeholder="$t('table.categoryDesc')" :autosize="{ minRows: 4, maxRows: 6}"
                        v-model="tempCategory.categoryDesc" type="textarea"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogCategoryFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="categoryFormStatus === 'create'" type="primary" @click="createCategory">{{ $t('table.save')
          }}
        </el-button>
        <el-button v-else type="primary" @click="updateCategory">{{ $t('table.save') }}</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>
  import {fetchCategoryTree, getCategory, addCategory, delCategory, putCategory} from '@/api/exam/subjectCategory'
  import {checkMultipleSelect, exportExcel, notifySuccess, isNotEmpty} from '@/utils/util'

  export default {
    name: "SubjectCategoryTree",
    props:{
      value: {
        type: String,
        default: ''
      },
      showCheckbox: {
        type: Boolean,
        default: false,
      },
    },
    dicts: ['sys_yes_no'],
    data(){
      return{
        treeData: [],
        tempCategory: {
          categoryName: undefined,
          categoryDesc: undefined,
          id: undefined,
          parentId: -1,
          status:'Y',
          sort: 30,
          type: 0
        },
        oExpandedKey: {},
        oTreeNodeChildren: {},
        defaultProps: {
          children: 'children',
          label: 'categoryName'
        },
        labelPosition: 'right',
        categoryFormStatus: '',
        dialogCategoryFormVisible: false,
        textMap: {
          update: '编辑',
          create: '新建'
        },
        // 表单校验规则
        categoryRules: {
          categoryName: [{required: true, message: '请输入分类名称', trigger: 'change'}]
        },
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        fetchCategoryTree(this.listQuery).then(response => {
          this.treeData = response.data;
        })
      },
      filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      nodeExpand(data) {
        const aChildren = data.children
        if (aChildren.length > 0) {
          this.oExpandedKey[data.id] = true
          this.oTreeNodeChildren[data.id] = aChildren
        }
        this.setExpandedKeys()
      },
      nodeCollapse(data) {
        this.oExpandedKey[data.id] = false
        // 如果有子节点
        this.treeRecursion(this.oTreeNodeChildren[data.id], (oNode) => {
          this.oExpandedKey[oNode.id] = false
        })
        this.setExpandedKeys()
      },
      setExpandedKeys() {
        const oTemp = this.oExpandedKey
        this.aExpandedKeys = []
        for (const sKey in oTemp) {
          if (oTemp[sKey]) {
            this.aExpandedKeys.push(parseInt(sKey))
          }
        }
      },
      treeRecursion(aChildren, fnCallback) {
        if (aChildren) {
          for (let i = 0; i < aChildren.length; ++i) {
            const oNode = aChildren[i]
            fnCallback && fnCallback(oNode)
            this.treeRecursion(oNode.children, fnCallback)
          }
        }
      },
      resetCategoryForm() {
        this.tempCategory = {
          categoryName: undefined,
          categoryDesc: undefined,
          id: undefined,
          parentId: this.currentCategoryId,
          sort: 30
        }
      },
      // 点击主分类按钮
      handleAddSuperCategory() {
        this.resetCategoryForm()
        this.tempCategory.parentId = -1
        this.categoryFormStatus = 'create'
        this.dialogCategoryFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataCategoryForm'].clearValidate()
        })
      },

      // 点击子分类按钮
      handleAddCategory() {
        if (this.currentCategoryId === '') {
          this.$message({
            message: '请选择分类',
            type: 'warning'
          })
          return
        }
        this.resetCategoryForm()
        this.categoryFormStatus = 'create'
        this.dialogCategoryFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataCategoryForm'].clearValidate()
        })
      },
      // 删除分类
      handleDeleteCategory() {
        if (this.currentCategoryId === '') {
          this.$message({
            message: '请选择要删除的记录',
            type: 'warning'
          })
          return
        }
        // TODO 检查是否有子分类
        // 检查分类下是否有题目
        fetchSubjectList(this.listQuery).then(response => {
          if (response.rows.length > 0) {
            this.$message({
              message: '该分类下还有题目',
              type: 'warning'
            })
            return
          }
          this.$confirm('确定要删除吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delCategory(this.currentCategoryId).then(() => {
              notifySuccess(this, '删除成功')
              this.getList()
              this.resetCategoryForm()
              this.onCancel()
            })
          })
        })
      },
      onCancel() {
        this.formStatus = ''
      },
      // 新建分类
      createCategory() {
        this.$refs['dataCategoryForm'].validate((valid) => {
          if (valid) {
            addCategory(this.tempCategory).then(() => {
              this.dialogCategoryFormVisible = false
              this.getList()
              notifySuccess(this, '创建成功')
            })
          }
        })
      },
      // 更新分类
      updateCategory() {
        this.$refs['dataCategoryForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.tempCategory)
            putCategory(tempData).then(() => {
              this.dialogCategoryFormVisible = false
              this.getList()
              notifySuccess(this, '更新成功')
            })
          }
        })
      },

      // 修改分类
      handleUpdateCategory() {
        if (this.currentCategoryId === '') {
          this.$message({
            message: '请选择分类',
            type: 'warning'
          })
          return
        }
        this.tempCategory = Object.assign({}, this.category) // copy obj
        this.categoryFormStatus = 'update'
        this.dialogCategoryFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataCategoryForm'].clearValidate()
        })
      },

      // 删除分类
      handleDeleteCategory() {
        if (this.currentCategoryId === '') {
          this.$message({
            message: '请选择要删除的记录',
            type: 'warning'
          })
          return
        }
        // TODO 检查是否有子分类
        // 检查分类下是否有题目
        fetchSubjectList(this.listQuery).then(response => {
          if (response.rows.length > 0) {
            this.$message({
              message: '该分类下还有题目',
              type: 'warning'
            })
            return
          }
          this.$confirm('确定要删除吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            delCategory(this.currentCategoryId).then(() => {
              notifySuccess(this, '删除成功')
              this.getList()
              this.resetCategoryForm()
              this.onCancel()
            })
          })
        })
      },

      // 点击分类
      getNodeData(data) {
        // 获取分类信息
        getCategory(data.id).then(response => {
          this.category = response.data
        })
        //this.value = data.id
        this.$emit("selected",  data);
      },

      nodeExpand(data) {
        const aChildren = data.children
        if (aChildren.length > 0) {
          this.oExpandedKey[data.id] = true
          this.oTreeNodeChildren[data.id] = aChildren
        }
        this.setExpandedKeys()
      },
      nodeCollapse(data) {
        this.oExpandedKey[data.id] = false
        // 如果有子节点
        this.treeRecursion(this.oTreeNodeChildren[data.id], (oNode) => {
          this.oExpandedKey[oNode.id] = false
        })
        this.setExpandedKeys()
      },
      checkHandler(data,nodes){
        this.$emit("checked",  nodes);
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .box-card{
    margin-right: 10px;
  }
  .category-header {
    /*margin: 12px;*/
  }

  .tree-container {
    /*padding-top: 10px;*/
  }

  .category-btn {
    margin: 5px;
    padding: 6px 13px;

  }

  .filter-tree {
    overflow: hidden;
  }
</style>
