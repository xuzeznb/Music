
<template>
  <HelloWorld />
<router-view></router-view>
</template>

<script lang="ts">
import HelloWorld from './components/HelloWorld.vue'
export default{
  components:{
  HelloWorld
  },
setup(){
const syahoew ="Hello Vue 3 + TypeScript + Vite"
return{
  syahoew
}
}

}
</script>

<style>

</style>
