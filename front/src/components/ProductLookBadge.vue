<template>
<span class="notify-badge" v-if="toggleBadge">{{modelName}}</span>
</template>

<script>
export default {
    name: "product-look-badge",
    props:['modelName','toggleBadge']
}
</script>

<style>
.notify-badge{
    position: absolute;
    right:-10px;
    top:10px;
    background:blue;
    text-align: center;
    border-radius: 30px 30px 30px 30px;
    color:white;
    padding:5px 10px;
    font-size:15px;
}
</style>