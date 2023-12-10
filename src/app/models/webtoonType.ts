export type comment = {
    id : number;
    userId : string;
    comment : string;
    webtoon : string;
}

export type Webtoon = {
    id : number;
    title : string;
    author : string;
    url : string;
    thumbnailUrl : string;
    genre : string[];
    likeCount : number;
    overallLikeCount : number;
    likeProportion : number;
    firstDate : string;
    dayOfWeek : string;
    platform : string;
    comments : comment[]
}

export type sort = {
    empty : boolean;
    sorted : boolean;
    unsorted : boolean;
}

export type pageable = {
    offset : number;
    sort : sort;
    pageNumber : number;
    pageSize : number;
    unpaged : boolean;
    paged : boolean;
}


export type WebtoonListPage = {
    totalPages : number;
    totalElements : number;
    size : number;
    content : Webtoon[]
    number : number;
    sort : sort;
    first : boolean;
    last : boolean;
    numberOfElements : number;
    pageable : pageable;
    empty : boolean;
}

