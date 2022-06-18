<template>
  <div>{{ songTitle }}</div>
</template>
<script lang="ts" setup>
import {useRoute} from "vue-router";
import {search} from "../utils/api";
import {ref} from "vue";

const aa = useRoute().query
console.log(aa.name)
const searchlist = ref()
search(aa.name).then(res => {
  searchlist.value = res
})

</script>
