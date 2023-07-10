import { createApp } from 'vue';

/* import bootstrap */
import "bootstrap/dist/css/bootstrap.min.css";

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core';
/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
/* import specific icons */
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

library.add(faMagnifyingGlass);

import App from './App.vue';

const app = createApp(App);
app.mount('#app');
app.component('font-awesome-icon', FontAwesomeIcon);

//import "bootstrap/dist/js/bootstrap.js"