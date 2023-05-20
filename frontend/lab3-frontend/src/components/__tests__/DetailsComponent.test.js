import { describe, it, expect, beforeEach } from "vitest";
import { setActivePinia, createPinia } from "pinia";
import { mount } from "@vue/test-utils";
import DetailsComponent from "../DetailsComponent.vue";

describe("DetailsComponent", () => {
    beforeEach(() => {
        setActivePinia(createPinia());
    });
    it("Text when editing", async () => {
        const wrapper = mount(DetailsComponent);
        wrapper.vm.selectedAd = 1;
        const result = wrapper.vm.editText(1);
        expect(result).toBe("Spremi");
    });
    it("Text when not editing", async () => {
        const wrapper = mount(DetailsComponent);
        wrapper.vm.selectedAd = -1;
        const result = wrapper.vm.editText(1);
        expect(result).toBe("Uredi");
    });
});
