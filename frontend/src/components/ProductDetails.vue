<template>
  <div v-if="currentproduct.id" class="edit-form">
    <h4>product</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          v-model="currentproduct.name"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input
          type="text"
          class="form-control"
          id="description"
          v-model="currentproduct.description"
        />
      </div>

      <div class="form-group">
        <label for="quantity">Quantity</label>
        <input
          class="form-control"
          id="quantity"
          required
          v-model="currentproduct.quantity"
          name="quantity"
        />
      </div>
    </form>

    <button class="badge badge-danger mr-2" @click="deleteproduct">
      Delete
    </button>

    <button type="submit" class="badge badge-success" @click="updateproduct">
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a product...</p>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import productDataService from "@/services/ProductDataService";
import product from "@/services/types/Product";

@Component
export default class productDetails extends Vue {
  private currentproduct = {} as product;
  private message: string = "";

  getproduct(id: string) {
    productDataService.get(id)
      .then((response) => {
        this.currentproduct = response.data;
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }


  updateproduct() {
    productDataService.update(this.currentproduct.id, this.currentproduct)
      .then((response) => {
        console.log(response.data);
        this.message = "The product was updated successfully!";
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteproduct() {
    productDataService.delete(this.currentproduct.id)
      .then((response) => {
        console.log(response.data);
        this.$router.push({ name: "products" });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.message = "";
    this.getproduct(this.$route.params.id);
  }
}
</script>

<style scoped>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
