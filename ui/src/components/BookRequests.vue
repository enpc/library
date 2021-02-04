<template>
<v-container>
    <v-tabs
      v-model="tab"
      align-with-title
    >
      <v-tabs-slider color="yellow"></v-tabs-slider>

      <v-tab>
        Поиск слова в книге
      </v-tab>

      <v-tab>
        Поиск слова в главе
      </v-tab>

      <v-tab>
        Ошибки
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item>
        <v-card flat>
          <book-word-count :bookName="bookName"/>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <chapter-word-count :bookName="bookName"/>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <errors :bookName="bookName"/>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
</v-container>
</template>

<script>
import BookWordCount from './BookWordCount.vue'
import ChapterWordCount from './ChapterWordCount.vue'
import Errors from './Errors.vue'

//import axios from 'axios'

export default {
  name: 'BookRequests',
  props:{
    bookName: String
  },
  components: {
    ChapterWordCount,
    BookWordCount,
    Errors
  },
  data:()=>({
    tab: null
  }),

  mounted(){
   // this.update()
  },

  methods:{

  }
}
</script>
