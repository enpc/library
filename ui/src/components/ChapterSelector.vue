<template>
 <v-container fluid>
  <v-slider
      v-model="chapter"          
      min=1
      :max="chaptersCount"
      label="Глава"
    >>
    <template v-slot:append>
      <v-text-field
        v-model="chapter"
        class="mt-0 pt-0"
        type="number"
        style="width: 100px"
        min="1"
        :max = "chaptersCount"
      ></v-text-field>
    </template>
  </v-slider>
 </v-container>
</template>

<script>

import axios from 'axios'

export default {
  name: 'BookRequests',
  model:{
    prop: "selected",
    event: "changed"
  },  
  props:{
    bookName: String,  
    selected: Number  
  },
  components: {
  },
  data:()=>({
    chaptersCount: 1,
    chapter: 1
  }),

  mounted(){
    this.update()
  },

  methods:{
    update(){
      if (this.bookName !=null){
        axios.get(`/api/book/${this.bookName}/chapters`)
        .then(response => {
          this.chaptersCount = response.data;
        })
      }
    }
  },
  watch:{
    bookName: function(){
        this.update();
      },

      chapter: function(val){
        this.$emit('changed', val);
      },

      selected: function(val){
        this.chapter = val;
      }
  }
}
</script>
