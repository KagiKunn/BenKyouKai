export default {
    name: 'CommonButton',
    props: {
        text:String,
        route:String
    },
    methods: {
        handleClick() {
            if (this.route) {
                this.$router.push(this.route);
            } else {
                alert("경로가 없으예");
            }
        }
    }
}