<template>
  <div class="app-container">

    <el-row :gutter="40">
      <el-col :span="24">
        <el-card class="title-info">
          <div slot="header">
            <span>班级详情</span>
          </div>

          <el-row>
            <el-col :span="18">
              <el-form ref="stageForm" :model="form" label-width="100px" size="mini">
                <el-row>
                  <el-col :span="8">
                    <el-form-item label="班级名称：">{{ classInfo.className }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="班级编号：">{{ classInfo.classCode }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="所属专业：">{{ classInfo.major.majorName }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="当前阶段：">{{ classInfo.stage }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="班级类型：">
                      <dict-tag :options="dict.type.teach_class_types" :value="classInfo.classType"/>
                    </el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="班级状态：">
                      <dict-tag :options="dict.type.teach_class_status" :value="classInfo.status"/>
                    </el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="开班时间：">{{ classInfo.startTime }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="结业时间：">{{ classInfo.endTime }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="在读人数：">{{ classInfo.readingCount }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="退学人数：">{{ classInfo.dropOutCount }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="就业人数：">{{ classInfo.employmentCount }}</el-form-item>
                  </el-col>

                  <el-col :span="8">
                    <el-form-item label="休学人数：">{{ classInfo.absenceCount }}</el-form-item>
                  </el-col>


                </el-row>
              </el-form>
            </el-col>
            <el-col :span="6">
              <div class="student-chart">
                <div class="chart" ref="chart" style="height: 150px; width: 100%;"></div>
              </div>
            </el-col>
          </el-row>


        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="40">
      <el-col :span="24">
        <el-card class="stage-list">
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                @click="handleAdd"
                v-hasPermi="['teach:classes:add']"
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
                v-hasPermi="['teach:classes:edit']"
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
                v-hasPermi="['teach:classes:remove']"
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
                v-hasPermi="['teach:classes:export']"
              >导出
              </el-button>
            </el-col>
            <right-toolbar @queryTable="getList"></right-toolbar>
          </el-row>
          <el-table v-loading="loading" :data="classStageList">
            <el-table-column type="selection" width="55" align="center"/>
            <!--                <el-table-column label="ID" align="center" prop="id"/>-->
            <el-table-column label="阶段" align="center" prop="stageName"/>
            <el-table-column label="起始时间" align="center" prop="startTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="结束时间" align="center" prop="endTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="阶段状态" align="center" prop="status">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.teach_stage_status" :value="scope.row.status"/>
              </template>
            </el-table-column>
            <el-table-column label="更新者" align="center" prop="updateBy"/>
            <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['teach:classes:edit']"
                >修改
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['teach:classes:remove']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>


    <!-- 添加或修改班级阶段对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级" prop="classId">
          <el-input v-model="classInfo.className" :disabled="true"/>
        </el-form-item>

        <el-form-item label="当前阶段" prop="stage">
          <el-select v-model="form.stageName" placeholder="请选择当前阶段" :disabled="form.id>0" clearable>
            <el-option
              v-for="stage in stageList"
              :key="stage.stageName"
              :label="stage.stageName"
              :value="stage.stageName"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="起始时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择起始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="阶段状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.teach_stage_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
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
  import {listClassStage, getClassStage, delClassStage, addClassStage, updateClassStage} from "@/api/teach/classStage";
  import {listStage} from "@/api/teach/stage";

  import echarts from 'echarts'
  import CountTo from "vue-count-to";

  require('echarts/theme/macarons');

  export default {
    name: "classDetails",
    dicts: ['teach_class_types', 'teach_class_status', 'teach_stage_status'],
    components: {CountTo},
    props: {
      classInfo: {
        type: Object,
        default: function () {

          var obj = {
            id: 0,
            major: {
              majorName: null,
            },
            stage: {
              stageName: null,
            }
          }

          return obj;

        }
      }
    },
    watch: {
      classInfo: {
        immediate:true,
        handler(newValue, oldValue) {
          if (this.classInfo==null) return;
          this.classStagequeryParams.classId = this.classInfo.id;
          this.getStageList();
          this.getList();
        },
        deep: true
      },


    },
    data() {
      return {
        // 遮罩层
        loading: true,

        // 是否显示弹出层
        open: false,

        // 非单个禁用
        single: true,

        // 非多个禁用
        multiple: true,
        title: null,
        // 查询参数
        classStagequeryParams: {
          pageNum: 1,
          pageSize: 10,
          classId: null,
          stageId: null,
          startTime: null,
          endTime: null,
          status: null,
        },

        // 学期阶段表格数据
        stageList: [],
        // 查询参数
        stageQueryParams: {
          stageName: null,
          stageCode: null,
          eduSystem: null,
          collegeId: null,
          status: null,
        },

        classStageList: [],

        // 表单参数
        form: {},

        // 表单校验
        rules: {
          classId: [
            {required: true, message: "班级ID不能为空", trigger: "blur"}
          ],
          stageId: [
            {required: true, message: "阶段ID不能为空", trigger: "blur"}
          ],
        },

        chart: {},
        chartData: [],

      }
    },
    methods: {

      /** 查询学期阶段列表 */
      getStageList() {
        this.stageList = [];

        this.stageQueryParams.collegeId = this.classInfo.collegeId;
        this.stageQueryParams.eduSystem = this.classInfo.major.majorType;

        listStage(this.stageQueryParams).then(response => {
          this.stageList = response.rows;
        });
      },

      /** 查询班级阶段列表 */
      getList() {
        this.loading = true;
        listClassStage(this.classStagequeryParams).then(response => {
          this.classStageList = response.rows;
          this.total = response.total;
          this.loading = false;
          this.initChart();
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
          classId: null,
          stageId: null,
          startTime: null,
          endTime: null,
          status: "0",
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
        this.title = "添加班级阶段";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getClassStage(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改班级阶段";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateClassStage(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addClassStage(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除班级阶段编号为"' + ids + '"的数据项？').then(function () {
          return delClassStage(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('teach/classes/export', {
          ...this.queryParams
        }, `classes_${new Date().getTime()}.xlsx`)
      },
      initChart() {
        this.chartData = [];
        this.chartData.push({name: '在读人数', value: this.classInfo.readingCount});
        this.chartData.push({name: '退学人数', value: this.classInfo.dropOutCount});
        this.chartData.push({name: '休学人数', value: this.classInfo.absenceCount});
        this.chartData.push({name: '离校人数', value: this.classInfo.levelCount});
        this.chartData.push({name: '毕业人数', value: this.classInfo.graduateCount});
        this.chartData.push({name: '就业人数', value: this.classInfo.employmentCount});

        if(this.$refs.chart==undefined) return;

        this.chart = echarts.init(this.$refs.chart, 'macarons')
        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
            {
              name: '学生状态统计',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              animationEasing: 'cubicInOut',
              animationDuration: 2600,
              label: {
                normal: {
                  formatter: function (param) {
                    return param.name + ': ' + Math.round(param.value)
                  }
                }
              },
              data: this.chartData
            }
          ]
        });

      }

    }
  }
</script>

<style lang="scss" scoped>
  .details-container {
    padding: 12px;

  }

  .stage-list {
    margin-top: 12px;
  }
</style>

