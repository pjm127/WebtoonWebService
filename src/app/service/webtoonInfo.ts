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