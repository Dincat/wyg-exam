<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="微信名称" prop="wxName">
        <el-input
          v-model="queryParams.wxName"
          placeholder="请输入微信名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信编号" prop="wxCode">
        <el-input
          v-model="queryParams.wxCode"
          placeholder="请输入微信编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AppID" prop="appId">
        <el-input
          v-model="queryParams.appId"
          placeholder="请输入AppID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="AppSecret" prop="appSecret">
        <el-input
          v-model="queryParams.appSecret"
          placeholder="请输入AppSecret"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Token" prop="token">
        <el-input
          v-model="queryParams.token"
          placeholder="请输入Token"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户号" prop="mchId">
        <el-input
          v-model="queryParams.mchId"
          placeholder="请输入商户号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付密钥" prop="payKey">
        <el-input
          v-model="queryParams.payKey"
          placeholder="请输入支付密钥"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第三方开放平台" prop="aesKey">
        <el-input
          v-model="queryParams.aesKey"
          placeholder="请输入第三方开放平台"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否默认" prop="isDefault">
        <el-input
          v-model="queryParams.isDefault"
          placeholder="请输入是否默认"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入所属用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['wechat:config:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wechat:config:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wechat:config:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wechat:config:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="微信名称" align="center" prop="wxName" />
      <el-table-column label="微信编号" align="center" prop="wxCode" />
      <el-table-column label="AppID" align="center" prop="appId" />
      <el-table-column label="AppSecret" align="center" prop="appSecret" />
      <el-table-column label="Token" align="center" prop="token" />
      <el-table-column label="Grant Type" align="center" prop="grantType" />
      <el-table-column label="商户号" align="center" prop="mchId" />
      <el-table-column label="支付密钥" align="center" prop="payKey" />
      <el-table-column label="第三方开放平台" align="center" prop="aesKey" />
      <el-table-column label="证书地址" align="center" prop="certPath" />
      <el-table-column label="是否默认" align="center" prop="isDefault" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="所属用户ID" align="center" prop="userId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wechat:config:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wechat:config:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改微信配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="70%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="160px">

        <el-row>
          <el-col :span="12">
            <el-form-item label="微信名称" prop="wxName">
              <el-input v-model="form.wxName" placeholder="请输入微信名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="微信编号" prop="wxCode">
              <el-input v-model="form.wxCode" placeholder="请输入微信编号" />
            </el-form-item>
          </el-col>
        </el-row>



        <el-row>
          <el-col :span="12">
            <el-form-item label="AppID" prop="appId">
              <el-input v-model="form.appId" placeholder="请输入AppID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="AppSecret" prop="appSecret">
              <el-input v-model="form.appSecret" placeholder="请输入AppSecret" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="Token" prop="token">
              <el-input v-model="form.token" placeholder="请输入Token" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商户号" prop="mchId">
              <el-input v-model="form.mchId" placeholder="请输入商户号" />
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="支付密钥" prop="payKey">
              <el-input v-model="form.payKey" placeholder="请输入支付密钥" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Aes Key" prop="aesKey">
              <el-input v-model="form.aesKey" placeholder="请输入Aes Key" />
            </el-form-item>
          </el-col>
        </el-row>



        <el-row>
          <el-col :span="12">
            <el-form-item label="证书地址">
              <file-upload v-model="form.certPath"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否默认">
              <el-radio-group v-model="form.isDefault">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>



        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="所属用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入所属用户ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listConfig, getConfig, delConfig, addConfig, updateConfig } from "@/api/wechat/config";

export default {
  name: "Config",
  dicts: ['sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 微信配置表格数据
      configList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wxName: null,
        wxCode: null,
        appId: null,
        appSecret: null,
        token: null,
        grantType: null,
        mchId: null,
        payKey: null,
        aesKey: null,
        certPath: null,
        isDefault: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wxName: [
          { required: true, message: "微信名称不能为空", trigger: "blur" }
        ],
        wxCode: [
          { required: true, message: "微信编号不能为空", trigger: "blur" }
        ],
        appId: [
          { required: true, message: "AppID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询微信配置列表 */
    getList() {
      this.loading = true;
      listConfig(this.queryParams).then(response => {
        this.configList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        wxName: null,
        wxCode: null,
        appId: null,
        appSecret: null,
        token: null,
        grantType: null,
        mchId: null,
        payKey: null,
        aesKey: null,
        certPath: null,
        isDefault: null,
        remark: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加微信配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getConfig(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConfig(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除微信配置编号为"' + ids + '"的数据项？').then(function() {
        return delConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('wechat/config/export', {
        ...this.queryParams
      }, `config_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
