<template>
  <div style="height: 60px;width: 100%;display: flex;justify-content: center;">
    <el-col :span="6" style="display:flex;justify-content: center;">
      <h2 style="margin-top: 20px;">网易云音乐</h2>
    </el-col>
    <el-col :span="6">
      <div style="line-height: 20px;display: flex;justify-content: center;">
        <router-link href="javascript:;" style="padding: 25px;color:black; text-decoration: none;" to="mymusic">我的
        </router-link>
        <router-link href="javascript:;" style="padding: 25px;color:black; text-decoration: none;" to="recommend">推荐
        </router-link>
        <router-link href="javascript:;" style="padding: 25px;color:black; text-decoration: none;" to="">动态
        </router-link>
      </div>
    </el-col>
    <el-col :span="6" style="line-height:60px;">
      <span style="width:80px;">
        <el-input :prefix-icon="Search" placeholder="请输入搜索的歌曲" placeholder-class="el-icon-search"
                  style="width:200px;border-radius:10px"
                  type="text" @keyup.enter="search"></el-input>
      </span>
    </el-col>
    <el-col :span="6" style="line-height:50px;">
      <span>
        <a v-if="login" href="javascript:;" style="padding-right: 15px;" @click="openlogin"> 登录</a>
        <el-dropdown>
          <el-image v-if="!login" :src="user.avatarUrl" style="width:50px ;height:50px;border-radius: 50%;"></el-image>
          <a v-if="!login" style="color:black;text-decoration: none;padding: 5px;">{{ user.nickname }}
            <el-icon class="el-icon--right" color="black">
              <arrow-down/>
            </el-icon></a>
          <!-- 下拉菜单 -->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <!-- 登录弹出框 -->
        <el-dialog v-model="store.state.dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false"
                   :show-close="false" width="30%">
          <el-tabs v-model="activeName">
            <el-tab-pane label="账号登录" name="first">
              <el-form ref="from" :model="loginForm" :rules="logimrole">
                <el-input v-model.number="loginForm.phone" placeholder="请输入手机号" type="number"/>
                <el-input v-model="loginForm.password" placeholder="请输入密码" type="password"/>
                <div style="display:flex;justify-content: center;">
                  <el-button type="success" @click="sign">登录</el-button>
                  <el-button @click="loginCancel">取消</el-button>
                </div>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="扫码登陆" name="second">
            </el-tab-pane>
            <el-tab-pane label="邮箱登录" name="third">
              <el-form ref="from" :model="emailForm" :rules="emailmrole">
                <el-input v-model="emailForm.email" placeholder="请输入邮箱" type="text"/>
                <el-input v-model.number="emailForm.password" placeholder="请输入密码" type="password"/>
              </el-form>
              <div style="display:flex;justify-content: center;">
                <el-button type="success" @click="emaillogin">登录</el-button>
                <el-button>取消</el-button>
              </div>
            </el-tab-pane>

            <el-tab-pane label="第三方登录" name="fourth">Task</el-tab-pane>
          </el-tabs>
        </el-dialog>
        <a v-if="login" href="javascript:;" style="padding: 5px;">注册</a>
      </span>
    </el-col>
  </div>
</template>
<script lang="ts" setup>
import {reactive, ref} from 'vue'
import store from '../store/index';
import {Search} from '@element-plus/icons-vue'
import request from '../config/request'
import {ElMessage} from 'element-plus';

const state: any = reactive({
  yzurl: ''
})
const cookie = localStorage.cookie
const size = ref<string>("180");
request.get("/login/status").then().catch((error) => {
  ElMessage.error("系统错误，请联系管理员!")
})
const search = () => {
  console.log("我被调用，马上进行搜索~~")
}
// 显示状态栏中的用户头像
const user: any = store.state.userInfo
// 默认显示第一个
const activeName = ref("first")
// 定义登录按钮
const openlogin = () => {
  store.commit("loginSwitch", true)
}
const loginCancel = () => {
  store.commit("loginSwitch", false)
}
// 时间戳
// console.log(new Date().getTime())
// 表单验证接口
interface userInfo {
  phone: any
  password: any
}

interface emailInfo {
  email: '',
  password: ''
}

// 表单验证
const loginForm: userInfo = reactive({
  phone: '',
  password: ''
})
// 邮箱表单
const emailForm: emailInfo = reactive({
  email: '',
  password: ''
})
// 验证手机规则
const logimrole = reactive({
  phone: [{required: 'true', message: '请输入合法电话', trigger: 'blur', pattern: "/^\d{15,17}$/"}],
  password: [{}]
})
// 验证账号规则
const emailmrole = reactive({
  email: [{required: 'true', message: '输入合法电话', trigger: 'blur', pattern: ' /^(?:\\w+\\.?)\\w+@(?:\\w+\\.)+\\w+$/'}],
  password: [{}]
})
//获取登录状态
const login = store.state.login
// 定义登录接口
const sign = () => {
  request.post("/login/cellphone", loginForm).then(res => {
    if (res.data.code == 200) {
      localStorage.setItem('cookie', res.data.cookie)
      ElMessage.success("登录成功")
      store.commit("userInfo", res.data.profile)
      store.commit("login", false)
      store.commit("loginSwitch", false)
      location.reload()
    } else {
      ElMessage.error(res.data.msg)
    }
  })
}
// 退出登录
const logout = () => {
  request.get("/logout").then(res => {
    if (res.data.code == '200') {
      store.commit("userInfo", '')
      localStorage.removeItem('cookie')
      ElMessage.success("退出成功")
      store.commit("login", true)
    }
    location.reload()
  })
}
const emaillogin = () => {
  request.post("/login", emailForm).then(res => {
    store.commit("loginSwitch", false)
    console.log(res)
    store.commit("userInfo", res.data.profile)
    store.commit("login", false)
    store.commit("loginSwitch", false)
    location.reload()
  })
  const state: any = reactive({
    loginUrl: ''
  })
}

</script>
<style scoped>
span {
  padding: 20px;
}

span > a {
  text-decoration: none;
  color: black;
  font-size: 12px;
  padding: 10px;
}

</style>
