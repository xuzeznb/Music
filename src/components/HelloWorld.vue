<template>
  <div style="height: 60px;width: 100%;display: flex;justify-content: center;">
    <el-col :span="6" style="display:flex;justify-content: center;">
      <h2 style="line-height: 30px;">网易云音乐</h2>
    </el-col>
    <el-col :span="6">
      <div style="line-height: 20px;display: flex;justify-content: center;">
        <router-link to="mymusic" style="padding: 25px;color:black; text-decoration: none;" href="javascript:;">我的</router-link>
        <router-link to="" style="padding: 25px;color:black; text-decoration: none;" href="javascript:;">推荐</router-link>
        <router-link to="" style="padding: 25px;color:black; text-decoration: none;" href="javascript:;">动态</router-link>
      </div>
    </el-col>
    <el-col :span="6" style="line-height:60px;">
      <span style="width:80px;">
        <el-input type="text" style="width:200px;" placeholder="请输入搜索的歌曲"></el-input>
      </span>
    </el-col>
    <el-col :span="6" style="line-height:50px;">
      <span>
        <a style="padding: 5px;" href="javascript:;" @click="openlogin" v-if="login"> 登录</a>
        <el-dropdown>
          <el-image v-if="!login" :src="user.avatarUrl" style="width:50px ;height:50px;border-radius: 50%;"></el-image>
          <a style="color:black;text-decoration: none;padding: 5px;" href="javascript:;"  v-if="!login" >{{ user.nickname }} <el-icon
              class="el-icon--right" color="black">
              <arrow-down />
            </el-icon></a>
          <!-- 下拉菜单 -->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <!-- 登录弹出框 -->
        <el-dialog v-model="dialogTableVisible" width="30%">
          <el-tabs v-model="activeName">
            <el-tab-pane label="账号登录" name="first">
              <el-form :model="loginForm" ref="from" :rules="logimrole">
                <el-input type="number" v-model.number="loginForm.phone" placeholder="请输入手机号" />
                <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" />
                <div style="display:flex;justify-content: center;">
                  <el-button type="success" @click="sign">登录</el-button>
                  <el-button>取消</el-button>
                </div>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="扫码登陆" name="second">Config</el-tab-pane>
            <el-tab-pane label="手机号登录" name="third">Role</el-tab-pane>
            <el-tab-pane label="第三方登录" name="fourth">Task</el-tab-pane>
          </el-tabs>
        </el-dialog>
        <a style="padding: 5px;" href="javascript:;" v-if="login">注册</a>
      </span>
    </el-col>
  </div>
</template>
<script setup lang="ts">
// 传值模板
import { ref, reactive } from 'vue'
import router from '../router';
import store from '../store/index';
import request from '../config/request'
import { ElMessage } from 'element-plus';
// 显示状态栏中的用户头像
const user: any = store.state.userInfo
// 默认登录窗不弹出
const dialogTableVisible = ref(false)
// 默认显示第一个
const activeName = ref("first")
// 定义登录按钮
const openlogin = () => {
  dialogTableVisible.value = true
}
// 表单验证接口
interface userInfo {
  phone: any
  password: any
}
// 表单验证
const loginForm: userInfo = reactive({
  phone: '',
  password: ''
})
// 验证手机规则
const logimrole = reactive({
  phone: [{ required: 'true', message: '请输入合法电话', trigger: 'blur', pattern: "/^\d{15,17}$/" }],
  password: [{}]
})
//获取登录状态
const login = store.state.login
// defineProps<{msg:string}>()
// 定义登录接口
const sign = () => {
  request.post("/login/cellphone", loginForm).then(res => {
    localStorage.setItem('cookie', res.data.cookie)
    if (res.data.code == 200) {
      store.commit("userInfo", res.data.profile)
      store.commit("login", false)
      dialogTableVisible.value = false
      location.reload()
    }
  })
}
// 退出登录
const logout = () => {

  request.get("/logout").then(res => {
    if (res.data.code == '200') {
    store.commit("userInfo",'')
      ElMessage.success("退出成功")
      store.commit("login", true)
    }
    location.reload()
  })
}
const state = reactive({
  nickname: '',
  avatarUrl: ''
})
</script>
<style scoped>
span {
  padding: 20px;
}

span>a {
  text-decoration: none;
  color: black;
  font-size: 12px;
  padding: 10px;
}
</style>
