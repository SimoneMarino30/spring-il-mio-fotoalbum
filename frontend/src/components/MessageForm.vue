<script>
import axios from "axios";
// import MyComponent from "./components/MyComponent.vue";

export default {
  data() {
    return {
      messages: {
        firstName: "",
        surname: "",
        email: "",
        message: "",


      }
    };
  },

  methods: {
    sendMessage() {
      //console.log(term);
      // check se axios funziona
      //console.log(axios);
      axios.post(`http://127.0.0.1:8080/api/album/messages`, {
        firstName: this.messages.firstName,
        surname: this.messages.surname,
        email: this.messages.email,
        message: this.messages.message,


      })
        // CIO' CHE VIENE LETTO DALL'API
        .then((response) => {
          this.messages.firstName = "";
          this.messages.surname = "";
          this.messages.email = "";
          this.messages.message = "";

          alert("Message saved")

          console.log(response.data);
        })
        .catch((error) => {
          console.error(error);
          alert("Message couldn't be saved")
        });
    }
  },
};
</script>

<template>
  <div class="row">
    <div class="col-12">
      <form method="POST" @submit.prevent>

        <label for="fname">First Name</label>
        <input type="text" id="fname" name="firstname" placeholder="Your name.." v-model="this.messages.firstName">

        <label for="lname">Last Name</label>
        <input type="text" id="lname" name="lastname" placeholder="Your last name.." v-model="this.messages.surname">

        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Email address</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
            placeholder="Your email address.." v-model="this.messages.email">
          <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>


        <label for="subject">Subject</label>
        <textarea id="subject" name="subject" placeholder="Write something.." style="height:200px"
          v-model="this.messages.message"></textarea>

        <button class="btn btn-success" @click="sendMessage">Submit</button>

      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>