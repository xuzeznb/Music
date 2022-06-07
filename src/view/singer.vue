<template>
<el-row>
<el-col :span="8" style="display:flex ; justify-content: right;margin-top: 20px;">
    <el-space direction="vertical" alignment="flex-start">
        <el-skeleton style="width: 250px" :loading="loading" animated :throttle="500">
            <template #template>
                <el-skeleton-item variant="image" style="width: 250px; height: 250px" />
            </template>
        </el-skeleton>
    </el-space>
</el-col>
<el-col :span="26">
<div>
aaa
</div>
</el-col>
</el-row>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
const avaterUrl = ref('')
const loading = ref(true)
const currentDate = new Date().toDateString()
</script>