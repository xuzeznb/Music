<template>
    <div>
      <audio v-if="showAudio"
             ref="audioRef"
             class="bgMusic"
             :autoplay="true"
             controls
             style="display: none"
             @ended="overAudio"
             @pause="onPause"
             @play="onPlay"
      >
      </audio>
    </div>
</template>
<script lang="ts" setup>
import {ref} from "vue";
const audioRef:any =ref(null)
const showAudio = ref(true)




</script>
<style scoped>
</style>
