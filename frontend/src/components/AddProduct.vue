<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="product.name"
          name="name"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <input
          class="form-control"
          id="description"
          required
          v-model="product.description"
          name="description"
        />
      </div>
      <div class="form-group">
        <label for="quantity">Quantity</label>
        <input
          class="form-control"
          id="quantity"
          required
          v-model="product.quantity"
          name="quantity"
        />
      </div>

      <button @click="saveproduct" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newproduct">Add</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import productDataService from "@/services/ProductDataService";
import Product from "@/services/types/Product";

@Component
export default class Addproduct extends Vue {
  private product: Product = {
    id: null,
    name: "",
    description: "",
    quantity: 0,
  };

  private submitted: boolean = false;

  saveproduct() {
    let data = {
      name: this.product.name,
      description: this.product.description,
      quantity: this.product.quantity,
    };

    productDataService.create(data)
      .then((response) => {
        this.product.id = response.data.id;
        console.log(response.data);
        this.submitted = true;
      })
      .catch((e) => {
        console.log(e);
      });
  }

  newproduct() {
    this.submitted = false;
    this.product = {} as Product;
  }
}
</script>

<style scoped>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
