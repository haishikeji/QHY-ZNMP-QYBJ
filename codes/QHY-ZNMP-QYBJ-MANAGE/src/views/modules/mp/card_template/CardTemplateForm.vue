<template>
  <div>
    <el-dialog :title="title" :close-on-click-modal="false" v-dialogDrag :visible.sync="visible">
      <el-form :model="inputForm" size="small" ref="inputForm" v-loading="loading"
        :class="method === 'view' ? 'readonly' : ''" :disabled="method === 'view'" label-width="120px">
        <el-row :gutter="20">
          <template v-if="!ours">
            <el-col :span="12">
              <el-form-item label="企业" prop="qy.id" :rules="[]">
                <GridSelect title="选择企业" labelName="name" labelValue="id" :value="inputForm.qy.id" :limit="1" @getValue="(value) => {
        inputForm.qy.id = value;
      }
      " :columns="[
      {
        prop: 'name',
        label: '企业名称',
      },
    ]" :searchs="[
      {
        prop: 'name',
        label: '企业名称',
      },
    ]" dataListUrl="/qy/enterprise/enterprise/list" queryEntityUrl="/qy/enterprise/enterprise/queryById">
                </GridSelect>
              </el-form-item>
            </el-col>
          </template>
          <el-col :span="12">
            <el-form-item label="字体颜色" prop="textColor" :rules="[]">
              <el-input v-model="inputForm.textColor" placeholder="请填写字体颜色"></el-input>
              <el-color-picker v-model="inputForm.textColor"></el-color-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="背景图片模板" prop="bgUrl" :rules="[]">
              <el-upload ref="bgUrl" v-if="visible" list-type="picture-card"
                :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=mp/card_template/cardTemplate`"
                :headers="{ token: $cookie.get('token') }" :on-preview="(file, fileList) => {
        $alert(
          `<img style='width:100%' src=' ${(file.response && file.response.url) || file.url
          }'/>`,
          {
            dangerouslyUseHTMLString: true,
            showConfirmButton: false,
            closeOnClickModal: true,
            customClass: 'showPic',
          }
        );
      }
      " :on-success="(response, file, fileList) => {
        inputForm.bgUrl = fileList
          .map(
            (item) =>
              (item.response && item.response.url) || item.url
          )
          .join('|');
      }
      " :on-remove="(file, fileList) => {
        $http
          .delete(
            `/sys/file/webupload/deleteByUrl?url=${(file.response && file.response.url) || file.url
            }`
          )
          .then(({ data }) => {
            $message.success(data);
          });
        inputForm.bgUrl = fileList
          .map((item) => item.url)
          .join('|');
      }
      " :before-remove="(file, fileList) => {
        return $confirm(`确定移除 ${file.name}？`);
      }
      " multiple :limit="1" :on-exceed="(files, fileList) => {
        $message.warning(
          `当前限制选择 1 个文件，本次选择了 ${files.length
          } 个文件，共选择了 ${files.length + fileList.length
          } 个文件`
        );
      }
      " :file-list="bgUrlArra">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="visible = false">关闭</el-button>
        <el-button size="small" type="primary" v-if="method != 'view'" @click="doSubmit()" v-noMoreClick>确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import GridSelect from "@/components/gridSelect";
import CardTemplateService from "@/api/mp/card_template/CardTemplateService";
export default {
  data() {
    return {
      title: "",
      method: "",
      visible: false,
      loading: false,
      bgUrlArra: [],
      ours: false,
      inputForm: {
        id: "",
        qy: {
          id: "",
        },
        type: "",
        textColor: "",
        bgUrl: "",
      },
    };
  },
  components: {
    GridSelect,
  },
  cardTemplateService: null,
  created() {
    this.cardTemplateService = new CardTemplateService();
  },
  methods: {
    init(method, id) {
      this.method = method;
      this.inputForm.id = id;
      let user = JSON.parse(localStorage.getItem("user"));
      let role = user.roleNames.split(",")[0];
      if (!(role == "系统管理员")) {
        this.inputForm.qy.id = user.companyDTO.id;
        this.ours = true;
      }
      if (method === "add") {
        this.title = `新建管理名片模板`;

      } else if (method === "edit") {
        this.title = "修改管理名片模板";
      } else if (method === "view") {
        this.title = "查看管理名片模板";
      }
      this.bgUrlArra = [];
      this.visible = true;
      this.loading = false;
      this.$nextTick(() => {
        this.$refs.inputForm.resetFields();
        if (method === "edit" || method === "view") {
          // 修改或者查看
          this.loading = true;
          this.cardTemplateService
            .queryById(this.inputForm.id)
            .then(({ data }) => {
              this.inputForm = this.recover(this.inputForm, data);
              this.inputForm.bgUrl.split("|").forEach((item) => {
                if (item.trim().length > 0) {
                  this.bgUrlArra.push({
                    name: decodeURIComponent(
                      item.substring(item.lastIndexOf("/") + 1)
                    ),
                    url: item,
                  });
                }
              });
              this.loading = false;
            });
        }
      });
    },
    // 表单提交
    doSubmit() {
      this.$refs["inputForm"].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.cardTemplateService
            .save(this.inputForm)
            .then(({ data }) => {
              this.visible = false;
              this.$message.success(data);
              this.$emit("refreshDataList");
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        }
      });
    },
  },
};
</script>
