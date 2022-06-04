
<template>
  <HelloWorld />
<router-view></router-view>
</template>

<script lang="ts">
import HelloWorld from './components/HelloWorld.vue'
import Music from './components/music.vue'
export default{
  components:{
    HelloWorld,
    Music
},
setup(){
return{

}
}

}
</script>

<style>

</style>
