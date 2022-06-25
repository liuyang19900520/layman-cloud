<template> 
  <div>
    <el-upload :action=signedUrl :headers="{
      'Content-Type': 'multipart/form-data'
    }" :data="dataObj" list-type="picture" :multiple="false" :show-file-list="showFileList" :file-list="fileList"
      :before-upload="beforeUpload" :on-remove="handleRemove" :on-success="handleUploadSuccess"
      :http-request="upload2S3" :on-preview="handlePreview">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>
<script>
import { policy } from './policy'
import { getUUID } from '@/utils'
import axios from 'axios'

export default {
  name: 'singleUpload',
  props: {
    value: String
  },
  computed: {
    imageUrl() {
      return this.value;
    },
    imageName() {
      if (this.value != null && this.value !== '') {
        return this.value.substr(this.value.lastIndexOf("/") + 1);
      } else {
        return null;
      }
    },
    fileList() {
      return [{
        name: this.imageName,
        url: this.imageUrl
      }]
    },
    showFileList: {
      get: function () {
        return this.value !== null && this.value !== '' && this.value !== undefined;
      },
      set: function (newValue) {
      }
    }
  },
  data() {
    return {
      signedUrl: "",
      dataObj: {
        policy: '',
        signature: '',
        key: '',
        ossaccessKeyId: '',
        dir: '',
        host: '',
        signedUrl: ''
        // callback:'',
      },
      dialogVisible: false
    };
  },
  methods: {
    emitInput(val) {
      this.$emit('input', val)
    },
    handleRemove(file, fileList) {
      this.emitInput('');
    },
    handlePreview(file) {
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      let _self = this;
      return new Promise((resolve, reject) => {
        policy(file.name).then(response => {
          _self.signedUrl = response.data.signedUrl;
          //_self.dataObj.signedUrl = response.data.signedUrl;
          // _self.dataObj.policy = response.data.policy;
          // _self.dataObj.signature = response.data.signature;
          // _self.dataObj.ossaccessKeyId = response.data.accessid;
          // _self.dataObj.key = response.data.dir + getUUID()+'_${filename}';
          // _self.dataObj.dir = response.data.dir;
          // _self.dataObj.host = response.data.host;
          resolve(true)
        }).catch(err => {
          reject(false)
        })
      })
    },
    upload2S3() {
      axios.put(this.signedUrl, this.fileList[0], {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        onUploadProgress: progressEvent => {
          let complete = (progressEvent.loaded / progressEvent.total * 100.).toFixed(2)
        }
      })
        .then(({ data }) => {
          this.$message({
            message: "上传成功",
            type: "success"
          });
        }).catch(
          err => {
            console.log(err)
          });
    },
    handleUploadSuccess(res, file) {
      console.log("上传成功...")
      this.showFileList = true;
      this.fileList.pop();
      this.fileList.push({ name: file.name, url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}", file.name) });
      this.emitInput(this.fileList[0].url);
    }
  }
}
</script>
<style>
</style>


