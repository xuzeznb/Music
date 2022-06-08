import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import store from './store/index'
import music from './components/music.vue'
const app:any = createApp(App)
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.component('Music',music)
app.use(store)
app.use(router)
app.mount('#app')
