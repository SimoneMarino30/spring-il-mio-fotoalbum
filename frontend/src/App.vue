<script>
import PhotoList from "./components/PhotoList.vue";
import { store } from "./data/store"
//import AppMain from "./components/AppMain.vue";

export default {
  data() {
    return {
      store,
      title: "My PhotoAlbum"
    };
  },

  components: {
    PhotoList,
  },
};
</script>

<template>
  <div class="container">

    <h1 class="text-center my-5">
      <font-awesome-icon icon="fa-solid fa-user-secret" />
      {{ title }}
    </h1>
    <PhotoList />
  </div>
</template>

<style lang="scss">
@use "./assets/scss/style.scss";
</style>
