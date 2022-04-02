<script setup>
import {ElButton} from 'element-plus'
import {defineCustomElement,ref} from 'vue'

defineCustomElement({
  props: {
    msg: String
  }
})

const count = ref(0)
</script>

<template>
  <h1>{{ msg }}</h1>
  <el-button type="primary" @click="count++">count is: {{ count }}</el-button>
</template>

<style scoped>
a {
  color: #42b983;
}
</style>
