<template>
<v-container>
    <v-text-field
      v-model="word"
      label="Слово для поиска"
    >
    </v-text-field>
    <v-btn 
      @click="search"
      :disabled="bookName === null"
    >
      Найти
    </v-btn>

  <v-card>
    <v-card-text>
      <v-data-table
        :headers="headers"
        :items="chapterStatistic"
        :options.sync="options"
        :server-items-length="total"
        class="elevation-1"
      ></v-data-table>
    </v-card-text>
  </v-card>
    
</v-container>
</template>

<script>

import axios from 'axios'

export default {
  name: 'BookWordCount',
  props:{
    bookName: String
  },
  components: {
  },
  data:()=>({
    word: "",
    chapterStatistic: [],
    options: {},
    total: 0,
     headers: [
          {
            text: 'Chapter',
            align: 'start',
            sortable: false,
            value: 'chapter',
          },{
            text: 'Count',
            align: 'start',
            sortable: false,
            value: 'count',
          }
        ],
  }),

  methods:{


  getItems(){
      axios.get(`/api/word/${this.bookName}`, {params:{
        word: this.word,
        page: this.options.page-1,
        size: this.options.itemsPerPage
      }})
      .then(response => {
        this.chapterStatistic = response.data;
      })
  },

    search(){
      axios.get(`/api/book/${this.bookName}/chapters`)
      .then(response => {
        this.total = response.data;
        this.getItems()
      })
    }
  },

  watch:{
    bookName: function(){
      this.total=0;
      this.chapterStatistic = [];
    },

    options: function(){
      this.getItems();
    }

  }
}
</script>
