import { reactive } from "vue";

export const store = reactive({
  photoList: [],
  photoFilteredList: [],
  endpoint: `http://127.0.0.1:8080/api/album/photos`,
});