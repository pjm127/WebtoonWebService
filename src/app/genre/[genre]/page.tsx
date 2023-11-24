import Card from '@/app/components/Card'
import WebtoonList from '@/app/components/WebtoonList'
import { getGenreWebtoon} from '@/app/service/webtoonInfo'
import React from 'react'
import Image from 'next/image'

type Props = {
  params : {genre : string}
}
function convertGenre(genre : string) : string{
  switch(genre){
    case 'action' : return '액션'
    case 'fantasy' : return '판타지'
    case 'romance' : return '로맨스'
    case 'mystery' : return '스릴러'
    case 'daily' : return '일상'
    case 'wuxia' : return '무협'
    case 'sports' : return '스포츠'
    default : return '장르없음'
  }
}

export default async function Genre({params : {genre}} : Props) {
  const genreWebtoons = await getGenreWebtoon(convertGenre(genre))
  if(!genreWebtoons) throw new Error(`${genre} 웹툰 존재하지 않음`);

  return (
    <>

      {genre} 페이지 입니다.
      <WebtoonList webtoonList={genreWebtoons} isRank={true}></WebtoonList>
    </>
  )
}
