<template>
  <div class="Homeapp">
    <div class="header">
      <h1>网易云音乐</h1>
      <div v-for="item in name">
        <ul>
          <router-link :to="item.path" class="header-list">{{ item.name }}</router-link>
        </ul>
      </div>
      <div class="header-login">
        <el-input style="width: 300px; height: 40px;padding-right:10px;" placeholder="请输入搜索歌名和歌单"></el-input>
        <el-button style="border-radius:none; width: 60px; height: 40px;" type="success">搜索</el-button>
      </div>
      <div>
        <ul style="padding-left: 10px;">
          <li class="header-list" @click="login">
            登录
          </li>
          <li class="header-list">
            注册
          </li>
        </ul>
        <el-dialog v-model="dialogFormVisible" width="30%">
          <el-form :model="form">
             <h2 style="text-align: center;padding-bottom: 40px;">请登录您的账号</h2>
            <el-form-item label="手机号 :" :label-width="formLabelWidth">
              <el-input v-model="form.phone" autocomplete="off" placeholder="请输入手机号" />
            </el-form-item>
            <el-form-item label="密码 :" :label-width="formLabelWidth">
              <el-input v-model="form.password" autocomplete="off" placeholder="请输入密码">
              </el-input>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="upload">登录</el-button>
            </span>
          </template>
        </el-dialog>
      </div>
    </div>
  </div>
  <div>
    <router-view />
  </div>
</template>

<script lang="ts">
// import request from '../config/request';
import { reactive, toRefs } from 'vue'
import '@/components/css/home.css'
export default {
  name: "home",
  setup() {
    const formLabelWidth = "100px";
    function login() {
        start.dialogFormVisible=true;
    }
  function upload() {
    // request.get('/login',{
    //   params:{
    //     phone: start.form.phone,
    //     password: start.form.password
    //   }
    // }).then(res=>{
    //  console.log(res)
   
    // })
    start.dialogFormVisible=false;
  }
  const start = reactive({
        name: [
        {
          path: "lead",
          name: "推荐",
        }, {
          path: "mymusic",
          name: "我的音乐",
        },
        {
          path: "follw",
          name: "关注",
        },
        {
          path: "Shopping",
          name: "商城",
        },
        {
          path: "",
          name: "音乐人",
        },
        {
          path: "",
          name: "下载客户端",
        },
      ],
      form: {
        phone:Number,
        password:String
      },
    dialogFormVisible:false
    })
    return {
      upload,
      ...toRefs(start), 
      login,
      formLabelWidth,
    }
  }
}

</script>

<style scoped>
</style>