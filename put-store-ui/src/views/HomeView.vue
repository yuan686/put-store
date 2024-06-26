<template>
  <div class="home" v-loading="loading">
    <div class="search">
      <el-form :model="queryParams" label-width="80px" :inline="true">
        <el-form-item label="样品编号">
          <el-input v-model="queryParams.sampleNum" placeholder="样品编号"></el-input>
        </el-form-item>
        <el-form-item label="海域">
          <el-input v-model="queryParams.seaAreaName" placeholder="海域"></el-input>
        </el-form-item>
        <el-form-item label="调查船">
          <el-input v-model="queryParams.shipName" placeholder="调查船"></el-input>
        </el-form-item>
        <el-form-item label="航次">
        <el-input v-model="queryParams.voyageName" placeholder="航次"></el-input>
        </el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form>
    </div>
    <div class="oper-btn">
      <!-- 自动上传版本 -->
      <el-upload
        class="upload-demo"
        action=""
        multiple
        :limit="1"
        :auto-upload="false"
        :before-upload="beforeUpload"
        :file-list="fileList"
        :on-change="handleUpload">
        <el-button  slot="trigger" type="primary">选择文件</el-button>
        <el-button type="success" @click="submit">上传</el-button>
      </el-upload>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="sampleNum"
        label="样品编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="shipName"
        label="调查船"
        width="180">
      </el-table-column>
      <el-table-column
        prop="voyageName"
        label="航次">
      </el-table-column>
      <el-table-column
        prop="seaAreaName"
        label="海域">
      </el-table-column>
      <el-table-column
        prop="position"
        label="站位">
      </el-table-column>
      <el-table-column
        prop="xcoordinate"
        label="x坐标">
      </el-table-column>
      <el-table-column
        prop="ycoordinate"
        label="y坐标">
      </el-table-column>
      <el-table-column
        prop="detailedAddress"
        label="详细地址">
      </el-table-column>
      <el-table-column
        prop="endDept"
        label="结束深度">
      </el-table-column>
      <el-table-column
        prop="heartLength"
        label="心长">
      </el-table-column>
      <el-table-column
        prop="storePosition"
        label="存放位置">
      </el-table-column>
      <el-table-column
        prop="saveStatus"
        label="保存状况">
      </el-table-column>
      <el-table-column
        prop="remark"
        label="备注">
      </el-table-column>
    </el-table>
    <el-pagination
      v-if="totalRecord > 0"
      background
      layout="prev, pager, next"
      :total="totalRecord"
      :current-page="queryParams.pageNum"
      @current-change="handleCurrentChange"
      >
    </el-pagination>

  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import axios from 'axios';

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data() {
    return {
      fileList: [],
      fileStore: null,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sampleNum: '',
        seaAreaName: '',
        shipName: '',
        voyageName: ''
      },
      tableData:[],
      totalRecord: 0,
      loading:false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    beforeUpload(file) {
      //判断文件type是否为application/vnd.ms-excel
      if (file.type !== 'application/vnd.ms-excel' || file.name.indexOf('.xls') === -1) {
        this.$message({
          showClose: true,
          message: '请上传正确的excel文件',
          type: 'error'
        });
        return false;
      }
      // 文件大小限制
      if (file.size / 1024 > 5 * 1024) {
        this.$message({
          showClose: true,
          message: '文件大小不能超过5M',
          type: 'error'
        });
      }
      return true
    },
    handleUpload(file, fileList) {
      console.log(file);
      this.fileStore = file.raw
    },
    submit() {
      if(!this.fileStore){
        this.$message.warning('请选择要上传的文件')
      }
      let formData = new FormData();
      formData.append('file', this.fileStore);
      this.loading = true
      axios.post('/api/store/upload', formData).then(resp => {
        console.log(resp);
        this.$message({
          showClose: true,
          message: '上传成功',
          type: 'success'
        })
        this.fileStore = null
        this.loading = false
        this.getList()
      }).catch(()=>{
        this.$message({
          showClose: true,
          message: '上传失败',
          type: 'error'
        })
        this.loading = false
      })
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    handleCurrentChange(pageNum) {
      this.queryParams.pageNum = pageNum
      this.getList()
    },
    getList() {
      this.loading = true
      axios.get('/api/store/list', {
        params: this.queryParams
      }).then(resp => {
        this.tableData = resp.data.data.list
        this.totalRecord = resp.data.data.total
        this.loading = false
      }).catch(() => {
        this.$message.error('查询失败')
        this.loading = false
      })
    },
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        sampleNum: '',
        seaAreaName: '',
        shipName: '',
        voyageName: ''
      }
      // TODO:可改造为监听pageNum和pageSize的变化，然后重新查询
      this.getList()
    }
  }
}
</script>
<style scoped lang="scss">
.home {
  
  .oper-btn {
    margin: 10px 0;
    .upload-demo {
      ::v-deep {
        .el-upload {
          margin-right: 10px;
        }
      }
    }
  }
}
</style>