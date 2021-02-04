<template>
<v-container>
    <chapter-selector :bookName="bookName" v-model="chapter" />
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
      {{chapterStatistic.word}}:{{chapterStatistic.count}}
    </v-card-text>
  </v-card>
    
</v-container>
</template>

<script>

import axios from 'axios'
import ChapterSelector from '@/components/ChapterSelector'

export default {
  name: 'ChapterWordCount',
  props:{
    bookName: String
  },
  components: {
    ChapterSelector
  },
  data:()=>({
    chapter: 1,
    word: "",
    chapterStatistic: {word: "Нет данных", count:null},

  }),

  methods:{
    search(){
      axios.get(`/api/word/${this.bookName}/${this.chapter}`, {params:{
        'word': this.word
      }})
      .then(response => {
        this.chapterStatistic = response.data;
      })
    }
  }
}
</script>
