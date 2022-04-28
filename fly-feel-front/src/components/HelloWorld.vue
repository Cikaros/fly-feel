<script setup>
import {defineCustomElement, ref} from 'vue'
import {ElButton} from 'element-plus'

defineCustomElement({
  props: {
    msg: String
  },
  components: {ElButton}
})

const count = ref(0)
</script>

<template>
  <h1>{{ msg }}</h1>
  <ElButton type="primary" @click="count++">count is: {{ count }}</ElButton>
</template>

<style scoped>
a {
  color: #42b983;
}
</style>
