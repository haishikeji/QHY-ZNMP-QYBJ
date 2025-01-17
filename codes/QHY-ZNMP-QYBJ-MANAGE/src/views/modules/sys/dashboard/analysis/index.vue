<template>
  <div class="el-scrollbar__wrap">
    <div class="el-scrollbar__view">
      <div class="analyze">

        <el-row class="m-3">
          <el-row :gutter="15">
            <el-col :span="6" >

              <router-link to="/qy/enterprise/EnterpriseList">

              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-location">
                  <img src="~@/assets/img/tit1.png" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">
                    {{ titleshow }}
                  </div>

                  <div class="flex">
                    <div v-if="isadmin == 1" class="card-panel-text2">
                      {{ info.t1.num1 }}
                    </div>
                    <div class="flex2">
                      <div class="card-panel-text3">
                        浏览&nbsp;&nbsp;{{ info.t1.num2 }}
                      </div>
                      <div class="card-panel-text3">
                        收藏&nbsp;&nbsp;{{ info.t1.num3 }}
                      </div>
                    </div>
                  </div>


                  <!-- <count-to :start-val="0" :end-val="info.ipVisit.num"
                                                  :duration="info.ipVisit.duration" class="card-panel-num"/> -->
                </div>
              </div>
              </router-link>
            </el-col>
            <el-col :span="6">
              <router-link to="/mp/card/CardInfoList">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-sell">
                  <img src="~@/assets/img/tit2.png" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">
                    发布名片
                  </div>
                  <div class="flex">
                    <div class="card-panel-text2">
                      {{ info.t2.num1 }}
                    </div>
                    <div class="flex2">
                      <div class="card-panel-text3">
                        浏览&nbsp;&nbsp;{{ info.t2.num2 }}
                      </div>
                      <div class="card-panel-text3">
                        收藏&nbsp;&nbsp;{{ info.t2.num3 }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </router-link>
            </el-col>
            <el-col :span="6">
              <router-link to="/qy/product/ProductList">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-visit">
                  <img src="~@/assets/img/tit3.png" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">
                    发布产品
                  </div>
                  <div class="flex">
                    <div class="card-panel-text2">
                      {{ info.t3.num1 }}
                    </div>
                    <div class="flex2">
                      <div class="card-panel-text3">
                        浏览&nbsp;&nbsp;{{ info.t3.num2 }}
                      </div>
                      <div class="card-panel-text3">
                        收藏&nbsp;&nbsp;{{ info.t3.num3 }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </router-link>
            </el-col>
            <el-col :span="6">
              <router-link to="/fw/all_employ/AllEmployList">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-people">
                  <img src="~@/assets/img/tit4.png" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">
                    发布服务
                  </div>
                  <div class="flex">
                    <div class="card-panel-text2">
                      {{ info.t4.num1 }}
                    </div>
                    <div class="flex2">
                      <div class="card-panel-text3">
                        浏览&nbsp;&nbsp;{{ info.t4.num2 }}
                      </div>
                      <div class="card-panel-text3">
                        收藏&nbsp;&nbsp;{{ info.t4.num3 }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </router-link>
            </el-col>
          </el-row>
        </el-row>


        <el-row class="m-3">
          <el-col :span="16">
            <el-card>
              <template>
                <div>
                  <div class="card-panel-text">当月企业每日浏览量及收藏量分析</div>
                </div>
              </template>
              <div style="height:270px; width: 100%;" id="cpnh_line"></div>
            </el-card>
          </el-col>

          <el-col :span="8">
            <el-card style="margin-left:15px ;">
              <template>
                <div class="card-panel-text">
                  <div class="card-panel-text">公司产品收藏排行&nbsp;TOP</div>
                </div>
              </template>
              <div style="padding-left: 20px;padding-right: 20px;height:270px">
                <el-table :data="cpnh_table" stripe height="270px" :row-style="{ height: '0' }"
                  :cell-style="{ padding: '5px', color: '#000000' }" :header-row-style="{ height: '0' }"
                  :header-cell-style="{ padding: '3px', color: '#000000' }">
                  <el-table-column prop="pz" label="" align="center"> </el-table-column>
                  <el-table-column prop="name" label="产品名称" min-width="150px" align="center"> </el-table-column>
                  <el-table-column prop="scNum" label="收藏量" align="center">
                    <template slot-scope="scope">
                      <div style="color: #4169E1;">
                        {{ scope.row.scNum }}
                      </div>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>


        </el-row>

        <el-row class="m-3">
          <el-col :span="16" class="flex">
            <el-card style="width: 50%;">
              <template>
                <div>
                  <div class="card-panel-text">公司名片收藏及浏览分析</div>
                </div>
              </template>
              <div style="height:270px; width: 100%;" id="cpnh_line2"></div>
            </el-card>
            <el-card style="margin-left:15px ;width: 50%;">
              <template>
                <div>
                  <div class="card-panel-text">公司服务收藏及浏览分析</div>
                </div>
              </template>
              <div style="height:270px; width: 100%;" id="cpnh_line3"></div>
            </el-card>
          </el-col>


          <el-col :span="8">
            <el-card style="margin-left:15px ;">
              <template>
                <div class="card-panel-text">
                  <div class="card-panel-text">公司名片收藏排行&nbsp;TOP</div>
                </div>
              </template>
              <div style="padding-left: 20px;padding-right: 20px;height:270px">
                <el-table :data="cpnh_table2" stripe height="270px" :row-style="{ height: '0' }"
                  :cell-style="{ padding: '5px', color: '#000000' }" :header-row-style="{ height: '0' }"
                  :header-cell-style="{ padding: '3px', color: '#000000' }">
                  <el-table-column prop="pz" label="" align="center"> </el-table-column>
                  <el-table-column prop="name" label="名片姓名" min-width="150px" align="center"> </el-table-column>
                  <el-table-column prop="num" label="收藏量" align="center">
                    <template slot-scope="scope">
                      <div style="color: #4169E1;">
                        {{ scope.row.num }}
                      </div>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>


        </el-row>



      </div>
    </div>
  </div>
</template>

<script>
import IndexService from '@/api/sys/IndexService'

export default {
  name: 'Analysis',
  components: {
  },
  data () {
    const rankList = []
    for (let i = 0; i < 7; i++) {
      rankList.push({
        name: '白鹭岛 ' + (i + 1) + ' 号店',
        total: 1234.56 - i * 100
      })
    }
    return {
      cpnh_table: [],
      cpnh_table2: [],
      tabs: {
        name: 'cellNum'
      },
      rankList,
      chart: {
        range: ''
      },
      info: {
        t1: {
          num1: 0,
          num2: 0,
          num3: 0,
          duration: 2600
        },
        t2: {
          num1: 0,
          num2: 0,
          num3: 0,
          duration: 3000
        },
        t3: {
          num1: 0,
          num2: 0,
          num3: 0,
          duration: 4000
        },
        t4: {
          num1: 0,
          num2: 0,
          num3: 0,
          duration: 4000
        }

      },

      data: {
        rows: [],
        columns: []
      },
      series: {
        type: 'bar',
        barWidth: 50,
        barGap: '50%',
        itemStyle: {
          normal: {
            color: (params) => {
              let colorList = ['#C33531', '#EFE42A', '#64BD3D', '#EE9201', '#29AAE3', '#B74AE5', '#0AAF9F', '#E89589', '#16A085', '#4A235A', '#C39BD3 ', '#F9E79F', '#BA4A00', '#ECF0F1', '#616A6B', '#EAF2F8', '#4A235A', '#3498DB']
              return colorList[params.dataIndex]
            }
          }
        }
      },
      call: '',
      indexService: null,
      isadmin: 0,
      titleshow: ''
    }
  },
  activated () {
    this.data.columns = ['日期', '销量']
    this.data.rows = []
    for (let i = 0; i < 12; i += 1) {
      this.data.rows.push({ '日期': `${i + 1}月`, '销量': Math.floor(Math.random() * 1000) + 200 })
    }
  },
  watch: {
    'tabs.name' (val) {
      if (val === 'visitNum') {
        this.$nextTick(_ => {
          this.$refs.visitNum.resize()
        })
      }
    }
  },

  methods: {

    getEnterprise () {
      var that = this
      this.indexService.getEnterprise().then(({ data }) => {
        let name = data[0].name
        if (name == undefined || name == '') {
          // 管理员
          this.isadmin = 1
          this.titleshow = '入驻企业'
          that.info.t1.num1 = data[0].qy_num
          that.info.t1.num2 = data[0].view_num
          that.info.t1.num3 = data[0].collect_num
        } else {
          this.isadmin = 0
          that.info.t1.num1 = name
          that.info.t1.num2 = data[0].ll_num
          that.info.t1.num3 = data[0].sc_num
        }
      })

      this.indexService.getCard().then(({ data }) => {
        that.info.t2.num1 = data[0].card_num
        that.info.t2.num2 = data[0].view_num
        that.info.t2.num3 = data[0].collect_num
      })

      this.indexService.getProduct().then(({ data }) => {
        that.info.t3.num1 = data[0].product_num
        that.info.t3.num2 = data[0].view_num
        that.info.t3.num3 = data[0].collect_num
      })

      this.indexService.getEmploy().then(({ data }) => {
        that.info.t4.num1 = data[0].employ_num
        that.info.t4.num2 = data[0].view_num
        that.info.t4.num3 = data[0].collect_num
      })

      // 公司产品收藏排名TOP
      this.indexService.productTop().then(({ data }) => {
        for (let i = 0; i < data.length; i++) {
          data[i].pz = i + 1
          data[i].scNum = data[i].scNum + ''
        }
        that.cpnh_table = data
      })

      // 公司名片收藏排名TOP
      this.indexService.getCardCollectTop().then(({ data }) => {
        for (let i = 0; i < data.length; i++) {
          data[i].pz = i + 1
          data[i].scNum = data[i].scNum + ''
        }
        that.cpnh_table2 = data
      })
    },

    line_fun1 (zzfx_data) {
      // 当月企业每日浏览量及收藏量分析
      this.indexService.getqyTime().then(({ data }) => {
        var myChart = this.$echarts.init(document.getElementById('cpnh_line'))

        let captions = []
        let values_1 = []
        let values_2 = []
        for (let i = 0; i < data.length; i++) {
          captions[i] = data[i].Date
          values_1[i] = data[i].collect_num
          values_2[i] = data[i].browse_num
        }

        if (zzfx_data && zzfx_data.length > 0) {
          zzfx_data.forEach((ele, index) => {
            let yf = parseInt(ele.yf)
            let arrayIndex = yf - 1
            arrayIndex = arrayIndex >= 0 ? arrayIndex : 0
            values[arrayIndex] = ele.count
          })
        }

        var option = {
          legend: {
            right: 1,
            top: 1,
            itemWidth: 20,
            itemHeight: 12,
            data: ['收藏量', '浏览量'],
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          tooltip: {
            trigger: 'axis',
            formatter: (params) => {
              let arr = [...params]
              let str = ''
              arr.forEach((item, index) => {
                str += item.seriesName + '    ' + item.data + '<br />'
              })
              str = arr[0].name + '日<br />' + str
              return str
            },
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          grid: {
            top: '18%', // 上边距
            right: '30', // 右边距
            left: '5', // 左边距
            bottom: '2%', // 下边距
            containLabel: true
          },
          xAxis: {
            name: '',
            nameTextStyle: {
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            boundaryGap: true,
            type: 'category',
            data: captions,
            axisTick: {
              show: false // 隐藏X轴刻度
            },
            axisLine: {
              lineStyle: {
                color: '#CCCCCC'
              }
            },
            axisLabel: {
              show: true,
              // interval: 0,
              textStyle: {
                color: '#333333',
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            }
          },
          yAxis: [{
            type: 'value',
            name: '',
            nameTextStyle: {
              align: 'right',
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            axisLabel: {
              color: '#333333',
              textStyle: {
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)'
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)',
                type: 'dashed'
              }
            }
          }],
          series: [{
            type: 'line',
            name: '收藏量',
            data: values_1,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#ffaa00',
              width: 2,
              shadowColor: 'rgba(31,205,129,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#ffaa00',
              borderColor: '#ffaa00',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          },
          {
            type: 'line',
            name: '浏览量',
            data: values_2,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#55aaff',
              width: 2,
              shadowColor: 'rgba(241,92,138,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#55aaff',
              borderColor: '#55aaff',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          }]
        }

        myChart.setOption(option)
      })
    },

    line_fun2 (zzfx_data) {
      // 公司名片收藏及浏览分析
      this.indexService.getCardTime().then(({ data }) => {
        var myChart = this.$echarts.init(document.getElementById('cpnh_line2'))

        let captions = []
        let values_1 = []
        let values_2 = []
        for (let i = 0; i < data.length; i++) {
          captions[i] = data[i].Date
          values_1[i] = data[i].collect_num
          values_2[i] = data[i].browse_num
        }

        if (zzfx_data && zzfx_data.length > 0) {
          zzfx_data.forEach((ele, index) => {
            let yf = parseInt(ele.yf)
            let arrayIndex = yf - 1
            arrayIndex = arrayIndex >= 0 ? arrayIndex : 0
            values[arrayIndex] = ele.count
          })
        }

        var option = {
          legend: {
            right: 1,
            top: 1,
            itemWidth: 20,
            itemHeight: 12,
            data: ['收藏量', '浏览量'],
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          tooltip: {
            trigger: 'axis',
            formatter: (params) => {
              let arr = [...params]
              let str = ''
              arr.forEach((item, index) => {
                str += item.seriesName + '    ' + item.data + 'kwh<br />'
              })
              str = arr[0].name + '日<br />' + str
              return str
            },
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          grid: {
            top: '18%', // 上边距
            right: '30', // 右边距
            left: '5', // 左边距
            bottom: '2%', // 下边距
            containLabel: true
          },
          xAxis: {
            name: '',
            nameTextStyle: {
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            boundaryGap: true,
            type: 'category',
            data: captions,
            axisTick: {
              show: false // 隐藏X轴刻度
            },
            axisLine: {
              lineStyle: {
                color: '#CCCCCC'
              }
            },
            axisLabel: {
              show: true,
              // interval: 0,
              textStyle: {
                color: '#333333',
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            }
          },
          yAxis: [{
            type: 'value',
            name: '',
            nameTextStyle: {
              align: 'right',
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            axisLabel: {
              color: '#333333',
              textStyle: {
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)'
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)',
                type: 'dashed'
              }
            }
          }],
          series: [{
            type: 'line',
            name: '收藏量',
            data: values_1,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#ffaa00',
              width: 2,
              shadowColor: 'rgba(31,205,129,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#ffaa00',
              borderColor: '#ffaa00',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          },
          {
            type: 'line',
            name: '浏览量',
            data: values_2,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#55aaff',
              width: 2,
              shadowColor: 'rgba(241,92,138,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#55aaff',
              borderColor: '#55aaff',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          }]
        }

        myChart.setOption(option)
      })
    },

    line_fun3 (zzfx_data) {
      // 公司服务收藏及浏览分析
      this.indexService.getfwTime().then(({ data }) => {
        var myChart = this.$echarts.init(document.getElementById('cpnh_line3'))

        let captions = []
        let values_1 = []
        let values_2 = []
        for (let i = 0; i < data.length; i++) {
          captions[i] = data[i].Date
          values_1[i] = data[i].collect_num
          values_2[i] = data[i].browse_num
        }

        if (zzfx_data && zzfx_data.length > 0) {
          zzfx_data.forEach((ele, index) => {
            let yf = parseInt(ele.yf)
            let arrayIndex = yf - 1
            arrayIndex = arrayIndex >= 0 ? arrayIndex : 0
            values[arrayIndex] = ele.count
          })
        }

        var option = {
          legend: {
            right: 1,
            top: 1,
            itemWidth: 20,
            itemHeight: 12,
            data: ['收藏量', '浏览量'],
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          tooltip: {
            trigger: 'axis',
            formatter: (params) => {
              let arr = [...params]
              let str = ''
              arr.forEach((item, index) => {
                str += item.seriesName + '    ' + item.data + 'kwh<br />'
              })
              str = arr[0].name + '日<br />' + str
              return str
            },
            textStyle: {
              fontFamily: 'PingFang SC',
              fontSize: 14,
              color: '#000000'
            }
          },
          grid: {
            top: '18%', // 上边距
            right: '30', // 右边距
            left: '5', // 左边距
            bottom: '2%', // 下边距
            containLabel: true
          },
          xAxis: {
            name: '',
            nameTextStyle: {
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            boundaryGap: true,
            type: 'category',
            data: captions,
            axisTick: {
              show: false // 隐藏X轴刻度
            },
            axisLine: {
              lineStyle: {
                color: '#CCCCCC'
              }
            },
            axisLabel: {
              show: true,
              // interval: 0,
              textStyle: {
                color: '#333333',
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            }
          },
          yAxis: [{
            type: 'value',
            name: '',
            nameTextStyle: {
              align: 'right',
              color: '#333333',
              fontSize: 14,
              fontFamily: 'PingFang SC'
            },
            axisLabel: {
              color: '#333333',
              textStyle: {
                fontSize: 14,
                fontFamily: 'PingFang SC'
              }
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)'
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(223, 223, 223, 1)',
                type: 'dashed'
              }
            }
          }],
          series: [{
            type: 'bar',
            name: '收藏量',
            data: values_1,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#ffaa00',
              width: 2,
              shadowColor: 'rgba(31,205,129,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#ffaa00',
              borderColor: '#ffaa00',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          },
          {
            type: 'bar',
            name: '浏览量',
            data: values_2,
            symbolSize: 5, // 标记的大小
            lineStyle: {
              color: '#55aaff',
              width: 2,
              shadowColor: 'rgba(241,92,138,0.5)', // 设置折线阴影
              shadowBlur: 4,
              shadowOffsetY: 7,
              shadowOffsetX: 2
            },
            itemStyle: {
              // 折线拐点标志的样式
              color: '#55aaff',
              borderColor: '#55aaff',
              borderWidth: 1
            },
            smooth: 0.4,
            emphasis: {
              scale: 1.5
            }
          }]
        }

        myChart.setOption(option)
      })
    }

  },

  mounted () {
    var roleIds = JSON.parse(window.localStorage.getItem('user')).roleIds
    if (roleIds.indexOf('1582260792211091457') != -1) {
      // 管理员
      this.isadmin = 1
      this.titleshow = '入驻企业'
    } else {
      // 非管理员
      this.isadmin = 0
      let aname = JSON.parse(window.localStorage.getItem('user')).companyDTO.name
      if (aname == undefined || aname == '') {
        this.titleshow = '未设置企业'
      } else {
        this.titleshow = aname
      }
    }
    this.indexService = new IndexService()

    this.getEnterprise()

    this.line_fun1()
    this.line_fun2()
    this.line_fun3()
  },
  destroyed () {

  }
}
</script>

<style scoped lang="less">
.analyze {

  .top-1 {
    top: 0.55rem;
  }

  .mt-0 {
    margin-top: 0;
  }

  .m-3 {
    margin-bottom: 0.75rem;
  }

  .right-0 {
    right: 200px;
  }

  .absolute {
    position: absolute;
  }

  .float-left {
    float: left;
  }

  .flex {
    display: flex;
  }

  .flex2 {
    display: flex;
    flex-direction: column;
  }

  .card-panel {
    border-radius: 6px;
    height: 130px;
    cursor: pointer;
    font-size: 12px;
    display: flex;
    overflow: hidden;
    color: #666;
    background: #ffffff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-sell {
        background: #6778fa;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-location {
        background: #36a3f7;
      }

      .icon-visit {
        background: #f4516c;
      }
    }

    .icon-sell {
      color: #6778fa;
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-location {
      color: #36a3f7;
    }

    .icon-visit {
      color: #f4516c;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 8px 4px 8px 4px;
      padding: 16px 12px 16px 12px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-text2 {
      line-height: 18px;
      width: 168px;
      color: #000000;
      font-size: 26px;
      margin-top: 18px;
    }

    .card-panel-text3 {
      line-height: 18px;
      color: #000000;
      font-size: 14px;
      margin-bottom: 12px;
      font-weight: 400;
    }

    .card-panel-description {
      float: left;
      font-weight: bold;
      margin: 26px;
      margin-left: 10px;
    }

    .card-panel-text {
      line-height: 18px;
      color: #000000;
      font-size: 16px;
      margin-bottom: 12px;
    }

    .card-panel-num {
      font-size: 20px;
    }

  }

}

.card-panel-text {
  line-height: 18px;
  font-weight: bold;
  color: #000000;
  font-size: 16px;
  margin-bottom: 12px;
}

.echart1 {
  height: 138px;
}




</style>
