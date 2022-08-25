<template>
  <div class="app-container">

    <el-row>
      <el-col :span="24">
        <el-card class="title-info">
          <div slot="header">
            <span>作业详情</span>
          </div>

          <el-row>
            <el-col :span="16">
              <el-form ref="stageForm" label-width="100px" size="mini">
                <el-row>
                  <el-col :span="24">
                    <el-form-item label="作业名称：">{{ homework.name }}</el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">
                    <el-form-item label="班级名称：">{{ homework.classes.className }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="班级类型：">
                      <dict-tag :options="dict.type.teach_class_types" :value="homework.classes.classType"/>
                    </el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="班级状态：">
                      <dict-tag :options="dict.type.teach_class_status" :value="homework.classes.status"/>
                    </el-form-item>
                  </el-col>


                  <el-col :span="6">
                    <el-form-item label="所属课程：">{{ homework.course.courseName }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="发布状态：">
                      <dict-tag :options="dict.type.exam_publish_status" :value="homework.publishStatus"/>
                    </el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="是否公开：">
                      <dict-tag :options="dict.type.sys_yes_no" :value="homework.isPublic"/>
                    </el-form-item>
                  </el-col>


                  <el-col :span="6">
                    <el-form-item label="布置老师：">{{ homework.teacher.teacherName }}</el-form-item>
                  </el-col>



                  <el-col :span="6">
                    <el-form-item label="完成情况：">{{ homework.submitCount }} / {{ homework.studentCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="合格分数：">{{ homework.passScore }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="良分数：">{{ homework.fineScore }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="优分数：">{{ homework.goodScore }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="满分：">{{ homework.fullScore }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="不及格人数：">{{ homework.failCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="及格人数：">{{ homework.passCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="良人数：">{{ homework.fineCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="优人数：">{{ homework.goodCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="满分人数：">{{ homework.fullCount }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="布置时间：">{{ parseTime(homework.publishTime) }}</el-form-item>
                  </el-col>

                  <el-col :span="6">
                    <el-form-item label="截止时间：">{{ parseTime(homework.deadLine, '{y}-{m}-{d}') }}</el-form-item>
                  </el-col>

                </el-row>
              </el-form>
            </el-col>
            <el-col :span="6">
              <div class="student-chart">
                <div class="chart" ref="chart" style="height: 260px; width: 100%;"></div>
              </div>
            </el-col>
          </el-row>


        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="40">
      <el-col :span="24">
        <el-card class="list-data">
          <div slot="header">
            <span>作答详情</span>
          </div>

          <student-homework :classId="homework.classes.id"/>

        </el-card>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  import studentHomework from "@/views/teach/student/homework";

  import echarts from 'echarts'
  import CountTo from "vue-count-to";


  require('echarts/theme/macarons');

  export default {
    name: "HomeworkDetails",
    dicts: ['exam_publish_status', 'teach_class_types', 'teach_class_status', 'teach_stage_status', 'sys_yes_no'],
    components: {CountTo, studentHomework},
    props: {
      homework: {
        type: Object,
        default: function () {

          var obj = {
            id: 0,

          }

          return obj;

        }
      }
    },
    watch: {
      homework: {
        immediate: true,
        handler(newValue, oldValue) {
          if (this.homework == null) return;
          this.initChart();
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


        chart: {},
        chartData: [],

      }
    },
    methods: {

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },


      initChart() {
        this.chartData = [];
        this.chartData.push({name: '不及格人数', value: this.homework.failCount});
        this.chartData.push({name: '及格人数', value: this.homework.passCount});
        this.chartData.push({name: '良人数', value: this.homework.fineCount});
        this.chartData.push({name: '优人数', value: this.homework.goodCount});
        this.chartData.push({name: '满分人数', value: this.homework.fullCount});

        if (this.$refs.chart == undefined) return;

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

  .app-container {
    padding-top: 0;
  }

  .details-container {
    padding: 12px;

  }

  .list-data {
    margin-top: 12px;
  }
</style>

