<template>
  <div v-cloak style="height: 60px;width: 100%;display: flex;justify-content: center;">
    <el-col :span="6" style="display:flex;justify-content: center;">
      <el-image src='./src/assets/img/logo.png' style="width: 40px;height: 40px;margin-top: 15px;padding-right: 10px"/>
      <h2 style="margin-top: 20px;">网易云音乐</h2>
    </el-col>
    <el-col :span="6">
      <div style="line-height: 20px;display: flex;justify-content: center;">
        <router-link style="padding: 25px;cursor: pointer; color:black; text-decoration: none;" to="mymusic">我的
        </router-link>
        <router-link style="padding: 25px;cursor: pointer; color:black; text-decoration: none;" to="recommend">推荐
        </router-link>
        <router-link style="padding: 25px;cursor: pointer;color:black; text-decoration: none;" to="">动态
        </router-link>
      </div>
    </el-col>
    <el-col :span="6" style="line-height:60px;">
      <span style="width:80px;">
        <el-input v-model="searchContent" :prefix-icon="Search" placeholder="请输入搜索的歌曲"
                  placeholder-class="el-icon-search"
                  style="width:200px;border-radius:10px"
                  type="text" @keyup.enter="searchs"></el-input>
      </span>
    </el-col>
    <el-col :span="6" style="width: 100%">
      <span style="width: 300px;line-height: 50px">
        <a v-show="!isLogin" style="cursor: pointer" @click="openlogin">登录</a>
        <el-dropdown>
          <div style="display:flex;line-height: 50px">
            <el-image v-show="isLogin" :src="profile?.avatarUrl??'' " lazy
                      style="width:50px ;height:50px;border-radius: 50%;"></el-image>
            <a style="padding-left: 5px">{{ profile?.nickname }}</a>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <!-- 登录弹出框 -->
        <el-dialog v-model="dialogTableVisible" :close-on-click-modal="false" :close-on-press-escape="false"
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
        <a v-show="!isLogin" style=" cursor: pointer; padding: 5px;">注册</a>
      </span>
    </el-col>
  </div>
</template>
<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Search} from '@element-plus/icons-vue'
import {useUserStore} from "../store/user";
import {storeToRefs} from "pinia";
import {signOut} from "../utils/api";
import {ElMessage} from "element-plus";
import router from "../router";

const {login, openPopup, closePopup, checkLogin} = useUserStore()
const {dialogTableVisible, profile, isLogin, searchof} = storeToRefs(useUserStore())
const activeName = ref('first')
//打开弹窗
const openlogin = () => {
  openPopup()
}
//取消弹窗
const loginCancel = () => {
  closePopup()
}
//登录
const sign = () => {
  login(loginForm.phone, loginForm.password)
}
onMounted(() => {
  checkLogin()
})
//搜索
const searchs = () => {
  router.push({path: "/search", query: {name: searchContent.value}})
}
//搜索内容
let searchContent = ref('');

//表单验证接口
interface userInfo {
  phone: string
  password: string
}

interface emailInfo {
  email: '',
  password: ''
}

// 表单验证
const loginForm: userInfo = reactive({
  phone: '',
  password: '',
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

//登出
const logout = () => {
  signOut().then(res => {
    if (res.code == 200) {
      ElMessage.success('退出登录！')
      location.reload()
    }
  })
}
const emaillogin = () => {
  // request.post("/login", emailForm).then(res => {
  //   store.commit("loginSwitch", false)
  //   console.log(res)
  //   store.commit("userInfo", res.data.profile)
  //   store.commit("login", false)
  //   store.commit("loginSwitch", false)
  //   location.reload()

  // })
  // const state: any = reactive({
  //   loginUrl: ''
  // })
}

interface LinkItem {
  first: string
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
