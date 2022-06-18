export interface result {
  //ID
  id: number,
  //类型
  type: number,
  //名称
  name: string,
  //文案
  copywriter: string,
  //图片URl
  picUrl: string,
  //喜不喜欢
  canDislike: boolean,
  // 曲目编号更新时间
  trackNumberUpdateTime: number,
  // 播放次数
  playCount: number,
  //轨道数（未知）
  // trackCount:number
  //高质量
  highQuality: boolean
}

export interface banners {
  imageUrl: string,
  targetId: number,
  adid: string,
  targetType: string,
  titleColor: string,
  typeTitle: string,
  url: string,
  exclusive: boolean,
  video: string,
  song: string,
  scm: string
}

export interface dailySongs {
  a: string,
  al: {
    id: number,
    name: string,
    pic: number,
    picUrl: string,
    oic_str: number,
    tns: []
  }
  alg: string,
  alia: []
  ar: [
    {
      id: number,
      name: string,
      tns: [],
      alias: []
    }
  ],
  cd: string
  cf: string,
  //版权
  copyright: number,
  cp: number,
  crbt: string,
  djId: number,
  dt: number,
  //娱乐标签
  entertainmentTags: string
  fee: number,
  ftype: number,
  h: {
    br: number,
    fid: number,
    size: number,
    vd: number,
    sr: number,
  }
  hr: {
    br: number,
    fid: number,
    size: number,
    vd: number,
    sr: number,
  }
  id: number
  l: {
    br: number,
    fid: number,
    size: number,
    vd: number,
    sr: number,
  },
  m: {
    br: number,
    fid: number,
    size: number,
    vd: number,
    sr: number,
  },
  mark: number
  mst: 9,
  mv: 0,
  name: string,
  no: 5,
  noCopyrightRcmd: string,
  originCoverType: number,
  originSongSimpleData: string
  pop: number,
  privilege: {
    chargeInfoList: [{
      rate: number
      changeUrl: string,
      chargeType: number,
      chargeMessage: string,
    }],
    cp: number,
    cs: boolean,
    dl: number,
    dlLevel: string,
    downloadMaxBrLevel: string
    downloadMaxbr: number,
    fee: number,
    fl: number,
    flLever: string,
    flag: number
    feeTrialPrivilege: {
      listenType: number,
      resConsumable: boolean,
      userConsumable: boolean,
    }
    id: number,
    maxBrLevel: string,
    maxbr: number,
    payed: number,
    pl: number,
    plLever: string
    playMaxBrLevel: string,
    playMaxbr: string,
    preSell: boolean,
    rscl: string,
    sp: number,
    st: number,
    subp: number,
    toast: number,
  }
  pst: number,
  publishTime: number,
  reason: string,
  resourceState: boolean,
  rt: string,
  rtUrl: string,
  rtUrls: [],
  rtype: number,
  rurl: string,
  s_id: number,
  single: number,
  songJumpInfo: string,
  sq: {
    br: number,
    fid: number,
    size: number,
    vd: number,
    sr: number,
  },
  st: number,
  t: number,
  tagePicList: string,
  v: number,
  version: number
}

export interface recommend {
  id: number,
  type: number,
  name: string,
  copywriter: string
  picUrl: string,
  playcount: number,
  createTime: number,
  creator: {
    avatarImgIdStr: string,
    backgroundImgIdStr: String,
    mutual: boolean,
    remarkName: string,
    datailDescription: string,
    expertTags: string,
    djStatus: number,
    followed: boolean,
    backgroundUrl: string,
    avatarImgId: number,
    backgroundImgId: number,
    userId: number,
    accountStatus: number,
    vipType: number,
    province: number,
    avatarUrl: string,
    authStatus: number,
    userType: number,
    nickname: string,
    gender: number,
    birthday: number,
    city: number,
    description: string,
    signature: boolean,
    authority: number,
  },
}

export interface albums {
  alias: [],
  artist: {
    albumSize: number,
    alias: [],
    briefDesc: string,
    id: number,
    imglvlId: number,
    imglvlId_str: string,
    imglvlUrl: string,
    musicSize: number,
    name: string,
    picId: number,
    picId_str: number,
    picUrl: string,
    topicPerson: number,
    trans: string
  },
  artists: {
    albumSize: number,
    alias: [],
    briefDesc: string,
    id: number,
    imglvlId: number,
    imglvlId_str: string,
    imglvlUrl: string,
    musicSize: number,
    name: string,
    picId: number,
    picId_str: number,
    picUrl: string,
    topicPerson: number,
    trans: string
  },
  blurPicUrl: string,
  briefDesc: string,
  commentThreadId: string,
  company: string,
  companyId: number,
  copyrightId: number,
  description: string,
  id: number,
  name: string,
  onSale: boolean,
  paid: boolean,
  pic: number,
  picId: number,
  picId_str: string,
  picUrl: string,
  publishTime: number,
  size: number,
  songs: '',
  status: number,
  tags: string,
  type: string,
}

export interface playlist {
  adType: number
  backgroundCoverId: number
  backgroundCoverUrl: string
  CloudTrackCount: number
  commentCount: number
  commentThreadId: string
  coverImgId: number
  coverImgId_str: string
  coverImgUrl: string
  createTime: number
  creator: {
    accountStatus: number
    anchor: boolean
    authStatus: number
    authenticationTypes: number
    authority: number
    avatarDetail: {
      identityIconUrl: string
      userType: number
      identityLever: number
    },
    avatarImgId: number
    avatarImgId_str: string
    avatarImgIdStr: string
    avatarUrl: string
    backgroundImgId: number
    backgroundImgIdStr: string
    backgroundUrl: string
    birthday: number
    city: number
    defaultAvatar: boolean
    description: string
    detailDescription: string
    djStatus: number
    expertTags: []
    experts: string
    followed: boolean
    gender: number
    mutual: boolean
    nickname: string
    province: number
    remarkName: string
    signature: string
    userId: number
    userType: number
    vipType: number
  },
  description: string
  englishTitle: string
  hightQuality: boolean
  id: number
  name: number
  newImported: boolean
  officialPlaylistType: string
  opRecommend: boolean
  ordered: boolean
  playCount: number
  privacy: number
  remixVideo: number
  shareCount: number
  shareUsers: string
  specialType: number
  status: number
  subscribed: boolean
  subscribedCount: number
  subscribers: []
  tags: []
  titleImage: number
  titleImageUrl: string
  trackCount: number
  trackNumberUpdateTime: number
  trackUpdateTime: number
  UpdateTime: string
  userId: number
  videoIds: string
  videos: string
}

export interface comment {
  code: number
  comments: [
    {
      content: string,
      liked: boolean,
      commentId: number,
      timeStr: string
      user: {
        avatarUrl: string
        nickname: string
        userId: number
        userType: number
        vipType: number
      }
    },
  ]
  hotComments: []
}

export interface searchInDetail {
  id: number,
  name: string,
  artists: [
    {
      id: number,
      name: string,
    }
  ],
  album: {
    id: number,
    name: string,
    artist: [
      id: number,
      name: string,
    ],
  }
}
