<template v-cloak>
  <HelloWorld/>
  <div class="content">
    <router-view></router-view>
  </div>
  <div class="bottom">
    <Music/>
  </div>
</template>

<script lang="ts" setup>
import HelloWorld from './components/HelloWorld.vue'
import Music from './components/music.vue'
import {userPlayerInit} from "./store/player";

userPlayerInit()
</script>

<style>
.content {
  margin-bottom: 100px;
}

.bottom {
  bottom: 0;
  position: fixed;
  width: 100%;
  z-index: 100000;
}

::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  --webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 6px;
}

::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background: rgb(220, 220, 220);
  --webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.5);
}

::-webkit-scrollbar-thumb:window-inactive {
  background: rgba(109, 109, 109, 0.4);
}

[v-clock] {
  display: none;
}

</style>
