import {defineStore} from 'pinia'
import {useLogin, verifyLogin} from "../utils/api";
import {useProfile} from "../interface/user";

export const useUserStore = defineStore('user', {
  state: () => {
    return {
      token: '',
      cookie: '',
      showLogin: false,
      profile: {} as useProfile,
      dialogTableVisible: false,
      loading: true
    }
  },
  getters: {
    isLogin: state => {
      return state.profile?.userId > 0
    },
  },
  actions: {
    async login(phone: string, password: string) {
      const res = await useLogin(phone, password)
      if (res.code === 200) {
        this.token = res.token
        this.cookie = res.cookie
        this.dialogTableVisible = false
        location.reload()
        localStorage.setItem('USER-TOKEN', this.token)
        localStorage.setItem('USER-COOKIE', this.cookie)
      }
    },
    async openPopup() {
      this.dialogTableVisible = true
    },
    async closePopup() {
      this.dialogTableVisible = false
    },
    async checkLogin() {
      const {data} = await verifyLogin()
      this.profile = data.profile
      this.showLogin = false
    },
    async openLoad() {
      this.loading = true
    },
    async closeLoading() {
      this.loading = false
    }

  }
})
