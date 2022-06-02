import createPrrsistedState from "vuex-persistedstate";
import { createStore} from "vuex";
export default createStore({
    plugins:[createPrrsistedState()],
    state:{
        login:true,
        userInfo:''
    },
    mutations:{
        login(state,value){
            state.login= value
        },
        userInfo(state,value){
            state.userInfo=value
        }
    },
    actions:{

    },
    modules:{

    }
})