<script>
// import MyComponent from "./components/MyComponent.vue";

export default {
  props: {
    photo: Object,
    id: Number,
    title: String,
    description: String,
    categories: Array,


  }


};
</script>

<template>
  <div class="gallery" v-if="visible == true">
    <a target="_blank" href="">
      <img :src="urlPic" alt="Cinque Terre">
      {{ title }}
    </a>
    <div class="desc">{{ description }}</div>
  </div>
</template>

<style lang="scss" scoped></style>