import router from "../router";
import store from "../store";

router.beforeEach((to, from, next) => {
  const cookie = localStorage.cookie
  if (cookie) {
    next();
  } else {
    store.commit("loginSwitch", true)
  }
})
