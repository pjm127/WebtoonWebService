import React from 'react'
import { getAllWebtoonInfo, getWebtoonRank } from '../service/webtoonInfo'
import Card from '../components/Card';
import WebtoonList from '../components/WebtoonList';

export default async function RankPage() {
  const webtoons = await getAllWebtoonInfo();
  const webtoonRanks = await getWebtoonRank(webtoons);

  return (
    <WebtoonList webtoonList={webtoonRanks} isRank={true}></WebtoonList>
  )
}
 