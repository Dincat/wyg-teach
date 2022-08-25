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
        <el-form-item label="课程名称" prop="courseName">
          <el-input
            v-model="queryParams.courseName"
            placeholder="请输入课程名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>



      <el-table ref="table" v-loading="loading" :data="courseList" @selection-change="handleSelectionChange"  @select="rowSelected">
        <el-table-column type="selection" width="55" align="center"/>

        <el-table-column label="主键" align="center" prop="id"/>
        <el-table-column label="课程名称" align="center" prop="courseName"/>
        <el-table-column label="启用状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="评分" align="center" prop="rating"/>
        <el-table-column label="封面" align="center" prop="cover">
          <template slot-scope="scope">
            <ImagePreview :src="scope.row.cover" :width="64"/>
          </template>
        </el-table-column>

        <el-table-column label="更新人" align="center" prop="updateBy"/>
        <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
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
  import {listCourse} from "@/api/teach/course";

  export default {
    name: "CourseSelector",
    dicts: ['teach_edu_system','sys_normal_disable'],

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
        courseList: [],
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
        collegeName:'',
        selectedItem: null
      };
    },

    methods: {

      /** 查询专业信息列表 */
      getList() {
        this.loading = true;
        listCourse(this.queryParams).then(response => {
          this.courseList = response.rows;
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
