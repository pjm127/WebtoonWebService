import path from "path"
import {promises as fs, readFile} from 'fs'
import { Webtoon } from "../models/webtoonType";

// export default async getWebtoonThumnail(){
//     fetch('',)
// }

export type WebtoonThum = {
    thumnail : String;
}

export async function getAllWebtoonInfo() : Promise<Webtoon[]>{
    const filePath = path.join(process.cwd(), 'data', 'webtoon.json');
    return fs.readFile(filePath, 'utf-8')
    .then<Webtoon[]>(JSON.parse)
}

// export async function getMainCircleThumnail() : Promise<string[]>{
//     const thumnails : string[] = [];
//     const webtoonsInfo = await getAllWebtoonInfo();   
// }


export async function getMainCircleThumnail() : Promise<WebtoonThum[]>{
    const filePath = path.join(process.cwd(), 'data', 'webtoonThum.json');
    return fs.readFile(filePath, 'utf-8')
    .then<WebtoonThum[]>(JSON.parse)
}

export async function getGenreCircleThumnail() : Promise<WebtoonThum[]>{
    const filePath = path.join(process.cwd(), 'data', 'genre.json');
    return fs.readFile(filePath, 'utf-8')
    .then<WebtoonThum[]>(JSON.parse)
}
export async function getWebtoonRank(webtoons : Webtoon[]) : Promise<Webtoon[]>{
    const webtoonRanks = webtoons.sort((webtoon1, webtoon2)=>{
        return webtoon2.likeProportion - webtoon1.likeProportion;
    })
    return webtoonRanks;
}

export async function getGenreWebtoon(genre : string) : Promise<Webtoon[]>{
    let webtoons = await getAllWebtoonInfo();
    const genreWebtoons =  webtoons.map((webtoon)=>{
        if(webtoon.genre.find(element=>element === genre)) return webtoon
    }).filter(element => element);
    if(genreWebtoons){
        return await getWebtoonRank(genreWebtoons as Webtoon[])
    }
    else {
         throw new Error(`${genre}웹툰 존재하지 않음!`) 
    }
}
export async function getNewWebtoon(){
    let webtoons = await getAllWebtoonInfo();
    
}
