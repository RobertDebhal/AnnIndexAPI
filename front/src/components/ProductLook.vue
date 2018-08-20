<template>
<a v-bind:href="pdpUrl">
    <img v-bind:src="productImage">
    <div class="caption">
        <p v-if=" brand != null && brand != ''" style="font-weight:bold"> {{ brand }} </p>
        <p> {{ productName }} </p>
    </div>
</a>
</template>

<script>
export default {
    name: "product-look",
    data() {
        return {
        }
    },
    props: ['productImage', 'productName', 'brand', 'pdpUrl']
}
</script>