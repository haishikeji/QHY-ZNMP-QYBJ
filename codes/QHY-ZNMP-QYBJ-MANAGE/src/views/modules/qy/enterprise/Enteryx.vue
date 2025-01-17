<template>
  <div>
    <el-dialog
      :title="title"
      :close-on-click-modal="false"
      v-dialogDrag
      :visible.sync="visible"
    >
      <el-form
        :model="inputForm"
        size="small"
        ref="inputForm"
        v-loading="loading"
        :class="method === 'view' ? 'readonly' : ''"
        :disabled="method === 'view'"
        label-width="120px"
      >
        <el-row :gutter="15">
          <el-col :span="12">
            <el-form-item
              label="服务年限(年)"
              prop="serviceNum"
              :rules="[
                {
                  required: true,
                  message: '服务年限不能为空',
                  trigger: 'blur',
                },
              ]"
            >
              <el-input
                v-model="inputForm.serviceNum"
                placeholder="请填写服务年限"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="附件"
              prop="attachments"
              :rules="[
                { required: true, message: '附件不能为空', trigger: 'blur' },
              ]"
            >
              <el-upload
                ref="attachments"
                v-if="visible"
                :action="`${this.$http.BASE_URL}/sys/file/webupload/upload?uploadPath=qy/qyservicerecords/qyServiceRecords`"
                :headers="{ token: $cookie.get('token') }"
                :on-preview="
                  (file, fileList) => {
                    $window.location.href =
                      (file.response && file.response.url) || file.url;
                  }
                "
                :on-success="
                  (response, file, fileList) => {
                    inputForm.attachments = fileList
                      .map(
                        (item) =>
                          (item.response && item.response.url) || item.url
                      )
                      .join('|');
                  }
                "
                :on-remove="
                  (file, fileList) => {
                    $http
                      .delete(
                        `/sys/file/webupload/deleteByUrl?url=${
                          (file.response && file.response.url) || file.url
                        }`
                      )
                      .then(({ data }) => {
                        $message.success(data);
                      });
                    inputForm.attachments = fileList
                      .map((item) => item.url)
                      .join('|');
                  }
                "
                :before-remove="
                  (file, fileList) => {
                    return $confirm(`确定移除 ${file.name}？`);
                  }
                "
                multiple
                :limit="5"
                :on-exceed="
                  (files, fileList) => {
                    $message.warning(
                      `当前限制选择 5 个文件，本次选择了 ${
                        files.length
                      } 个文件，共选择了 ${
                        files.length + fileList.length
                      } 个文件`
                    );
                  }
                "
                :file-list="attachmentsArra"
              >
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">添加相关附件</div>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="visible = false">关闭</el-button>
        <el-button
          size="small"
          type="primary"
          v-if="method != 'view'"
          @click="doSubmit()"
          v-noMoreClick
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import QyServiceRecordsService from "@/api/qy/qyservicerecords/QyServiceRecordsService";
import EnterpriseService from "@/api/qy/enterprise/EnterpriseService";
import FirmCertificationService from "@/api/qy/firmcertification/FirmCertificationService";
export default {
  data() {
    return {
      title: "",
      method: "",
      visible: false,
      loading: false,
      attachmentsArra: [],
      inputForm: {
        id: "",
        qyId: "",
        serviceStarte: "",
        serviceNum: "",
        attachments: "",
      },
    };
  },
  components: {},
  qyServiceRecordsService: null,
  enterpriseService: null,
  FirmCertificationService: null,
  created() {
    this.qyServiceRecordsService = new QyServiceRecordsService();
    this.enterpriseService = new EnterpriseService();
    this.FirmCertificationService = new FirmCertificationService();
  },
  methods: {
    init(method, id, starteTime) {
      this.method = method;
      this.inputForm.qyId = id;
      this.inputForm.serviceStarte = starteTime;
      if (method === "add") {
        this.title = `服务延续`;
      }
      this.visible = true;
      this.loading = false;
      this.$nextTick(() => {
        this.$refs.inputForm.resetFields();
      });
    },
    // 表单提交
    doSubmit() {
      this.$refs["inputForm"].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.qyServiceRecordsService
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

  
