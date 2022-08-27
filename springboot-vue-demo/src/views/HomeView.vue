<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">Add</el-button>
      <i class="el-button" type="primary" style="width:5%; font-size: 12px">Import</i>
      <i class="el-button" type="primary" style="width:5%; font-size: 12px">Export</i>
    </div>
    <div style="margin: 10px 0">
      <el-input clearable v-model="search" placeholder="Search keyword" style="width:20%"></el-input>
      <i class="el-button" @click="load" style="background-color : PowderBlue; margin-left: 5px;width:5%; font-size: 12px">Search</i>
    </div>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column prop="id" label="ID" />
    <el-table-column prop="username" label="User Name"/>
    <el-table-column prop="nickName" label="Nickname" />
    <el-table-column prop="age" label="Age" />
    <el-table-column prop="gender" label="Gender" />
    <el-table-column prop="address" label="Address" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
        >Edit</el-button
        >
        <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
          <template #reference>
            <el-button link type="primary" size="small">Delete</el-button>
          </template>
        </el-popconfirm>

      </template>
    </el-table-column>
  </el-table>
    <div><el-dialog
        title="提示"
        :model-value="dialogVisible"
        width="30%"
        @close="dialogVisible=false"
    >
      <el-form :model="form" label-width="120px">
        <el-form-item label="User Name">
          <el-input v-model="form.username" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="Nickname">
          <el-input v-model="form.nickName" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="Age">
          <el-input v-model="form.age" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="Gender">
          <el-radio v-model="form.gender" label="female">Female</el-radio>
          <el-radio v-model="form.gender" label="male">Male</el-radio>
          <el-radio v-model="form.gender" label="secret">Keep secret</el-radio>
        </el-form-item>
        <el-form-item label="Address">
          <el-input v-model="form.address" style="width:80%"></el-input>
        </el-form-item>
      </el-form>
      <div style="display: flex">
        <div style="flex: 1"></div>
      <span slot="footer" class="dialog-footer" style="flex: 1">
        <el-button type="primary" @click="save">Confirm</el-button>
        <el-button @click="dialogVisible = false">Cancel</el-button>
        </span>
        <div style="flex: 1"></div>
      </div>
    </el-dialog></div>
    <div style="margin:10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />


    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";
export default {
  name: 'HomeView',
  data(){
    return{
      form:{},
      dialogVisible: false,
      currentPage: 1,
      total: 0,
      pageSize:10,
      search:'',
      tableData : [
        // {
        //   date: '2016-05-03',
        //   name: 'Tom',
        //   address: 'No. 189, Grove St, Los Angeles',
        // },
        // {
        //   date: '2016-05-02',
        //   name: 'Tom',
        //   address: 'No. 189, Grove St, Los Angeles',
        // },
        // {
        //   date: '2016-05-04',
        //   name: 'Tom',
        //   address: 'No. 189, Grove St, Los Angeles',
        // },
        // {
        //   date: '2016-05-01',
        //   name: 'Tom',
        //   address: 'No. 189, Grove St, Los Angeles',
        // },
      ]
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
      request.get("/user",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search:this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
            this.total = res.data.total
      })
    },
    add(){
      this.dialogVisible=true,
          this.form={}
    },
    save() {
      if(this.form.id){ //更新
        request.put("/user",this.form).then(res => {
          if (res.code=="0"){
            this.$message({
              message: 'Operation success',
              type: 'success'
            });
            this.dialogVisible = false;
            this.load();
          }else {
            this.$message({
              message: 'res.msg',
              type: 'error'
            });
          }
          this.dialogVisible = false
        })
      }else{ //新增
        request.post("/user",this.form).then(res => {
          console.log(res)
          this.dialogVisible = false
        })
      }

    },
    handleDelete(id){
      console.log(id)
      request.delete("/user/" + id).then(res => {
        if (res.code=="0"){
          this.$message({
            message: 'Delete success',
            type: 'success'
          });
        }else {
          this.$message({
            message: 'res.msg',
            type: 'error'
          });
        }
        this.load()
      })
    },
    handleEdit(row)
    {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleSizeChange()
    {
      this.load()
    },
    handleCurrentChange()
    {
      this.load()
    }

  }
}

</script>
