import {createApp} from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import store from './store/index'
import './interface/extend'
import music from './components/music.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {createPinia} from "pinia";
import VueLazyload from 'vue-lazyload'

const app: any = createApp(App)
app.config.devtools = true
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(VueLazyload)
app.component('Music', music)
app.use(createPinia())
app.use(store)
app.use(router)
app.mount('#app')
