import {createApp} from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import store from './store/index'
import musicPlayer from "./store/musicPlayer";
import music from './components/music.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './config/defend'
import lodash from 'lodash'

const app: any = createApp(App)
app.config.globalProperties.$lodash = lodash
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(ElementPlus)
app.use(lodash)
app.component('Music', music)
app.use(store, musicPlayer)
app.use(router)
app.mount('#app')


