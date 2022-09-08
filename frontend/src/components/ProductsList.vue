<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by title"
          v-model="title"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Products List</h4>
      <ul class="list-group">
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(product, index) in products"
          :key="index"
          @click="setActiveproduct(product, index)"
        >
          {{ product.name }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllproducts">
        Remove All
      </button>
      <button class="m-3 btn btn-sm btn-danger" @click="exportProducts">
        Export
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentproduct.id">
        <h4>Product</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentproduct.name }}
        </div>
        <div>
          <label><strong>Description:</strong></label>
          {{ currentproduct.description }}
        </div>
        <div>
          <label><strong>Quantity:</strong></label>
          {{ currentproduct.quantity }}
        </div>

        <a
          class="badge badge-warning"
          :href="'/products/' + currentproduct.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a product...</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import productDataService from "@/services/ProductDataService";
import product from "@/services/types/Product";

@Component
export default class productsList extends Vue {
  private products: product[] = [];
  private currentproduct = {} as product;
  private currentIndex: number = -1;
  private title: string = "";

  retrieveproducts() {
    productDataService.getAll()
      .then((response) => {
        this.products = response.data;
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveproducts();
    this.currentproduct = {} as product;
    this.currentIndex = -1;
  }

  setActiveproduct(product: product, index: number) {
    this.currentproduct = product;
    this.currentIndex = index;
  }

  removeAllproducts() {
    productDataService.deleteAll()
      .then((response) => {
        console.log(response.data);
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  exportProducts() {
    productDataService.export().then((response) => {
        var FILE = window.URL.createObjectURL(new Blob([response.data]));
        var docUrl = document.createElement('a');
        docUrl.href = FILE;
        docUrl.setAttribute('download', 'file.pdf');
        document.body.appendChild(docUrl);
        docUrl.click();
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  searchTitle() {
    productDataService.findByTitle(this.title)
      .then((response) => {
        this.products = response.data;
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  mounted() {
    this.retrieveproducts();
  }
}
</script>

<style scoped>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
