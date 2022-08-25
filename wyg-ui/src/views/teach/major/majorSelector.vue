<template>
  <div class="app-container">
    <!-- 授权用户 -->
    <el-dialog title="选择专业" :visible.sync="visible" width="900px" top="5vh" append-to-body>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="所属院校" prop="collegeId">
          <el-input disabled
             v-model="collegeName"
          />

        </el-form-item>
        <el-form-item label="专业名称" prop="majorName">
          <el-input
            v-model="queryParams.majorName"
            placeholder="请输入专业名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="专业类型" prop="majorType">
          <el-select v-model="queryParams.majorType" placeholder="请选择专业类型" clearable>
            <el-option
              v-for="dict in dict.type.teach_edu_system"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
            v-hasPermi="['teach:major:add']"
          >新增
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['teach:major:edit']"
          >修改
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['teach:major:remove']"
          >删除
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['teach:major:export']"
          >导出
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table ref="table" v-loading="loading" :data="majorList" @selection-change="handleSelectionChange"  @select="rowSelected">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="ID" align="center" prop="id"/>
        <el-table-column label="专业名称" align="center" prop="majorName"/>
        <el-table-column label="专业类型" align="center" prop="majorType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.teach_edu_system" :value="scope.row.majorType"/>
          </template>
        </el-table-column>

        <el-table-column label="所属院校" align="center" prop="college.deptName"/>
        <el-table-column label="创建者" align="center" prop="createBy"/>
        <el-table-column label="创建时间" align="center" prop="createTime" width="120">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新者" align="center" prop="updateBy"/>
        <el-table-column label="更新时间" align="center" prop="updateTime" width="120">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
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

      <div slot="footer" class="dialog-footer">
        <el-button @click="handleSelect()">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
  import {listMajor} from "@/api/teach/major";

  export default {
    name: "MajorSelector",
    dicts: ['teach_edu_system'],

    data() {
      return {
        // 遮罩层
        loading: true,
        // 遮罩层
        visible: false,
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
        // 专业信息表格数据
        majorList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          majorName: null,
          majorType: null,
          collegeId: null,
        },
        // 表单参数
        form: {},
        collegeName:'123',
        selectedItem: null
      };
    },

    methods: {

      /** 查询专业信息列表 */
      getList() {
        this.loading = true;
        listMajor(this.queryParams).then(response => {
          this.majorList = response.rows;
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
          majorName: null,
          majorType: null,
          collegeId: null,
          remark: null,
          tenantId: null,
          tenantCode: null,
          createId: null,
          createBy: null,
          createTime: null,
          updateId: null,
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加专业信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getMajor(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改专业信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateMajor(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addMajor(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除专业信息编号为"' + ids + '"的数据项？').then(function () {
          return delMajor(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/major/export', {
          ...this.queryParams
        }, `major_${new Date().getTime()}.xlsx`)
      },

      rowSelected(selection, row){
        this.$refs.table.clearSelection()
        if(selection.length === 0){  //判断election是否有值存在
          return
        }
        if(row){
          this.selectedItem = row
          this.$refs.table.toggleRowSelection(row, true)
        }
      },

      // 显示弹框
      show() {
        this.getList();
        this.visible = true;
      },
      handleSelect() {
        this.$emit("select", this.selectedItem);
        this.visible = false;
      }
    }
  };
</script>
