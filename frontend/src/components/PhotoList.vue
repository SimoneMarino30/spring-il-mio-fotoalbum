<script>
import axios from "axios";
import MessageForm from "./MessageForm.vue";

export default {
  data() {
    return {
      photos: [],
      filteredPhotos: [],
      search: "",
    };
  },

  components: {
    MessageForm
  },

  created() {
    this.fetchPhotos();
  },
  methods: {
    fetchPhotos() {
      axios
        .get("http://localhost:8080/api/album/photos")
        .then((response) => {
          this.photos = response.data;
          this.filteredPhotos = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    searchPhotos() {
      this.filteredPhotos = this.photos.filter((photo) =>
        photo.title.toLowerCase().includes(this.search.toLowerCase())
      );
    },
  },
};
</script>

<template>
  <div class="container">
    <div class="input-group">
      <input @keyup.enter="searchPhotos" type="search" class="form-control" v-model="search"
        placeholder="Search by title" />
      <button class="btn btn-outline-secondary" @click="searchPhotos">
        Search
      </button>
    </div>

    <div class="container text-center">
      <div class="row">
        <div class="col-12">
          <div v-for="photo in filteredPhotos">
            <div v-if="photo.visible == true" class="gallery">
              <a target="_blank" :href="photo.urlPic">
                <img :src="photo.urlPic" alt="Cinque Terre">
              </a>
              <div class="desc">{{ photo.title }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <MessageForm />
  </div>
</template>
<style lang="scss">
/* The grid: Four equal columns that floats next to each other */
div.gallery {
  margin: 5px;
  border: 1px solid #ccc;
  float: left;
  width: 180px;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 100%;
  height: auto;
}

div.desc {
  padding: 15px;
  text-align: center;
}

/***************
 */
/* Style inputs with type="text", select elements and textareas */
input[type=text],
select,
textarea {
  width: 100%;
  /* Full width */
  padding: 12px;
  /* Some padding */
  border: 1px solid #ccc;
  /* Gray border */
  border-radius: 4px;
  /* Rounded borders */
  box-sizing: border-box;
  /* Make sure that padding and width stays in place */
  margin-top: 6px;
  /* Add a top margin */
  margin-bottom: 16px;
  /* Bottom margin */
  resize: vertical
    /* Allow the user to vertically resize the textarea (not horizontally) */
}

/* Style the submit button with a specific background color etc */
input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

/* When moving the mouse over the submit button, add a darker green color */
input[type=submit]:hover {
  background-color: #45a049;
}

/* Add a background color and some padding around the form */
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>